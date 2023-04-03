package ru.romanow.serialization.commands

import org.apache.avro.SchemaBuilder
import org.apache.avro.generic.GenericData
import org.apache.avro.generic.GenericRecordBuilder
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.slf4j.LoggerFactory
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.romanow.serialization.avro.AvroInnerData
import ru.romanow.serialization.avro.AvroPublicData
import ru.romanow.serialization.avro.AvroStatus
import ru.romanow.serialization.avro.AvroTestObject
import ru.romanow.serialization.model.createTestObject
import ru.romanow.serialization.services.avroFromJson
import ru.romanow.serialization.services.avroToJson

@ShellComponent
@ShellCommandGroup("AVRO")
class AvroCommand {
    private val logger = LoggerFactory.getLogger(AvroCommand::class.java)

    @ShellMethod(key = ["generated"], value = "Serialization and deserialization to JSON using AVRO generated schema")
    fun generated() {
        val testObjectSchema = AvroTestObject.getClassSchema()
        logger.info("Generated scheme:\n{}", testObjectSchema.toString(true))

        var testObject = AvroTestObject.newBuilder()
            .setCode(RandomUtils.nextInt(0, 100))
            .setMessage(RandomStringUtils.randomAlphanumeric(10))
            .setStatus(AvroStatus.DONE)
            .setInnerData(
                AvroInnerData.newBuilder()
                    .setCode(RandomStringUtils.randomAlphanumeric(15))
                    .setPriority(RandomUtils.nextInt(10, 15)).build()
            )
            .setPublicData(
                listOf(
                    AvroPublicData.newBuilder().setKey(RandomStringUtils.randomAlphabetic(8))
                        .setData(RandomStringUtils.randomAlphanumeric(15)).build(),
                    AvroPublicData.newBuilder().setKey(RandomStringUtils.randomAlphabetic(8))
                        .setData(RandomStringUtils.randomAlphanumeric(15)).build()
                )
            )
            .build()

        val json = avroToJson(testObject, testObjectSchema)
        logger.info("Serialized object '{}' to JSON", json)

        testObject = avroFromJson(json, testObjectSchema, AvroTestObject::class.java)
        logger.info("Deserialized object '{}'", testObject)
    }

    @ShellMethod(key = ["manual"], value = "Serialization and deserialization to JSON using AVRO manual schema")
    fun manual() {
        val statusSchema = SchemaBuilder
            .enumeration("status")
            .symbols("DONE", "FAIL", "PAUSED");
        val innerDataStatus = SchemaBuilder
            .record("InnerData")
            .fields()
            .requiredString("code")
            .requiredInt("priority")
            .endRecord();
        val publicDataSchema = SchemaBuilder
            .record("PublicData")
            .fields()
            .requiredString("key")
            .requiredString("data")
            .endRecord();
        val listSchema = SchemaBuilder
            .array().items(publicDataSchema);
        val testObjectSchema = SchemaBuilder
            .record("TestObject")
            .namespace("ru.romanow.serialization.avro")
            .fields()
            .requiredString("message")
            .requiredInt("code")
            .name("status").type(statusSchema).noDefault()
            .name("innerData").type(innerDataStatus).noDefault()
            .name("publicData").type(listSchema).noDefault()
            .endRecord()

        logger.info("Manual scheme:\n{}", testObjectSchema.toString(true))

        val testObject = createTestObject()
        var avroTestObject = GenericRecordBuilder(testObjectSchema)
            .set("message", testObject.message)
            .set("code", testObject.code)
            .set("status", testObject.status)
            .set(
                "innerData", GenericRecordBuilder(innerDataStatus)
                    .set("code", testObject.innerData?.code)
                    .set("priority", testObject.innerData?.priority)
                    .build()
            )
            .set("publicData", GenericData.Array(listSchema, testObject.publicData))
            .build()

        val json = avroToJson(avroTestObject, testObjectSchema)
        logger.info("Serialized object '{}' to JSON", json)

        avroTestObject = avroFromJson(json, testObjectSchema)
        logger.info("Deserialized object '{}'", avroTestObject)
    }
}
