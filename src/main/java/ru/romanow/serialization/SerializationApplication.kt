package ru.romanow.serialization

import com.jayway.jsonpath.JsonPath
import org.apache.avro.SchemaBuilder
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.apache.commons.lang3.RandomUtils.nextInt
import org.slf4j.LoggerFactory
import ru.romanow.serialization.avro.AvroInnerData
import ru.romanow.serialization.avro.AvroPublicData
import ru.romanow.serialization.avro.AvroStatus
import ru.romanow.serialization.avro.AvroTestObject
import ru.romanow.serialization.generated.ProtobufObjectProto
import ru.romanow.serialization.model.*
import ru.romanow.serialization.services.*
import java.util.*

private val logger = LoggerFactory.getLogger("main")

private const val XML_DATA_FILE = "/data/xml-data.xml"
private const val JSON_DATA_FILE = "/data/json-data.json"
private const val XPATH = "//key[text()='KEY1']"
private const val JSON_PATH = "$..[?(@.key == 'KEY1')].key"

fun main() {
//    testJson()
//    testXml()
//    validateXml()
//    testXPath()
//    testJsonPath()
//    testBson()
//    testMsgPack()
//    testProtobuf()
//    testAvroGenerated()
    testAvro()
}

private fun testAvroGenerated() {
    logger.info("\n==================== Start testAvroGenerated ====================")
    val testObjectSchema = AvroTestObject.getClassSchema()
    logger.info("Generated scheme:\n'{}'", testObjectSchema.toString(true))
    var avroTestObject = AvroTestObject.newBuilder()
        .setResultCode(nextInt(0, 100))
        .setMessage(randomAlphanumeric(10))
        .setStatus(AvroStatus.DONE)
        .setInnerData(
            AvroInnerData.newBuilder()
                .setCode(randomAlphanumeric(15))
                .setPriority(nextInt(10, 15)).build()
        )
        .setPublicData(
            listOf(
                AvroPublicData.newBuilder().setKey(RandomStringUtils.randomAlphabetic(8))
                    .setData(randomAlphanumeric(15)).build(),
                AvroPublicData.newBuilder().setKey(RandomStringUtils.randomAlphabetic(8))
                    .setData(randomAlphanumeric(15)).build()
            )
        )
        .build()
    val json: String = avroToJson(avroTestObject, testObjectSchema)
    logger.info("Serialized object '{}'", json)
    avroTestObject = avroFromJson(json, testObjectSchema, AvroTestObject::class.java)
    logger.info("Deserialized object '{}'", avroTestObject)
    logger.info("\n==================== Finish testAvroGenerated ====================")
}

private fun testAvro() {
    logger.info("\n==================== Start testAvro ====================")
    val statusSchema = SchemaBuilder
        .enumeration("AvroStatus")
        .namespace("ru.romanow.serialization.avro")
        .symbols("DONE", "FAIL", "PAUSED")
    val innerData = SchemaBuilder
        .record("AvroInnerData")
        .namespace("ru.romanow.serialization.avro")
        .fields()
        .requiredString("code")
        .requiredInt("priority")
        .endRecord()
    val publicDataSchema = SchemaBuilder
        .record("AvroPublicData")
        .namespace("ru.romanow.serialization.avro")
        .fields()
        .requiredString("key")
        .requiredString("data")
        .endRecord()
    val listSchema = SchemaBuilder
        .array()
        .prop("java-class", "java.util.List")
        .items(publicDataSchema)
    val testObjectSchema = SchemaBuilder
        .record("AvroTestObject")
        .namespace("ru.romanow.serialization.avro")
        .fields()
        .requiredString("message")
        .requiredInt("resultCode")
        .name("status").type(statusSchema).noDefault()
        .name("innerData").type(innerData).noDefault()
        .name("publicData").type(listSchema).noDefault()
        .endRecord()

    logger.info("Generated scheme:\n'{}'", testObjectSchema.toString(true))
    var testObject = createTestObject()
    val json: String = avroToJson(testObject, testObjectSchema)
    logger.info("Serialized object '{}'", json)
    testObject = avroFromJson(json, testObjectSchema, TestObject::class.java)
    logger.info("Deserialized object '{}'", testObject)
    logger.info("\n==================== Finish testAvro ====================")
}

private fun testJsonPath() {
    logger.info("\n==================== Start testJsonPath ====================")
    val json = readFromFile(JSON_DATA_FILE)
    logger.info("Read JSON from file:\n{}", json)
    val result = JsonPath.read<List<String>>(json, JSON_PATH)
    logger.info("JsonPath '{}' evaluates {}", JSON_PATH, result)
    logger.info("\n==================== Finish testJsonPath ====================")
}

private fun testXPath() {
    logger.info("\n==================== Start testXPath ====================")
    val xml = readFromFile(XML_DATA_FILE)
    logger.info("Read XML from file:\n{}", xml)
    val result = findByXPath(xml, XPATH)
    logger.info("XPath '{}' evaluates {}", XPATH, result)
    logger.info("\n==================== Finish testXPath ====================")
}

private fun testProtobuf() {
    logger.info("\n==================== Start testProtobuf ====================")
    val testObject = ProtobufObjectProto.TestObject
        .newBuilder()
        .setResultCode(nextInt(0, 100))
        .setMessage(RandomStringUtils.randomAlphabetic(10))
        .setStatus(ProtobufObjectProto.Status.FAIL)
        .setInnerData(ProtobufObjectProto.InnerData.newBuilder().setCode("123").setPriority(100).build())
        .build()
    logger.info("Serialize object '{}' to Protobuf", testObject)
    val serializedData = serialize(testObject)
    logger.info("{}", Base64.getEncoder().encodeToString(serializedData))
    val parsedObject = parseFrom(serializedData, ProtobufObjectProto.TestObject.parser())
    logger.info("\n{}", parsedObject)
    logger.info("\n==================== Finish testProtobuf ====================")
}

private fun testJson() {
    logger.info("\n==================== Start testJson ====================")
    val testObject = createTestObject()
    logger.info("Serialize object '{}' to JSON", testObject)
    val json = toJson(testObject)
    logger.info("\n{}", json)
    val newObject = fromJson(json, NewTestObject::class.java)
    logger.info("{}", newObject)
    logger.info("\n==================== Finish testJson ====================")
}

private fun testXml() {
    logger.info("\n==================== Start testXml ====================")
    val testObject: TestObject = createXmlTestObject()
    logger.info("Serialize object '{}' to XML", testObject)
    val xml: String = toXml(testObject)
    logger.info("\n{}", xml)
    val newObject: XmlTestObject = fromXml(xml)!!
    logger.info("{}", newObject)
    logger.info("\n==================== Finish testXml ====================")
}

private fun validateXml() {
    logger.info("\n==================== Start validateXml ====================")

    val xml = readFromFile(XML_DATA_FILE)

    logger.info("Read XML from file:\n{}", xml)
    logger.info("XML valid: {}", validate(xml))
    logger.info("\n==================== Finish validateXml ====================")
}

private fun testBson() {
    logger.info("\n==================== Start testBson ====================")
    val testObject = createTestObject()
    logger.info("Serialize object '{}' to BSON", testObject)
    val bson = toBson(testObject)
    logger.info("\n{}", Base64.getEncoder().encodeToString(bson))
    val newObject = fromBson(bson, TestObject::class.java)
    logger.info("{}", newObject)
    logger.info("\n==================== Finish testBson ====================")
}

private fun testMsgPack() {
    logger.info("\n==================== Start testMsgPack ====================")
    val testObject = createTestObject()
    logger.info("Serialize object '{}' to MsgPack", testObject)
    val bson = toMsgpack(testObject)
    logger.info("\n{}", Base64.getEncoder().encodeToString(bson))
    val newObject = fromMsgpack(bson, TestObject::class.java)
    logger.info("{}", newObject)
    logger.info("\n==================== Finish testMsgPack ====================")
}

private fun readFromFile(fileName: String): String =
    object {}.javaClass.getResource(fileName)!!.readText()

private fun createTestObject() =
    TestObject(
        message = randomAlphanumeric(10),
        resultCode = nextInt(0, 100),
        status = Status.DONE,
        innerData = buildInnerData(),
        publicData = listOf(buildPublicData(), buildPublicData())
    )

private fun createXmlTestObject() =
    XmlTestObject(
        message = randomAlphanumeric(10),
        resultCode = nextInt(0, 100),
        status = Status.DONE,
        innerData = buildInnerData(),
        publicData = listOf(buildPublicData(), buildPublicData())
    )

private fun buildPublicData() =
    PublicData(RandomStringUtils.randomAlphabetic(8), randomAlphanumeric(15))

private fun buildInnerData() =
    InnerData(randomAlphanumeric(15), nextInt(10, 15))
