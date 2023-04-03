package ru.romanow.serialization.commands

import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.RandomUtils
import org.slf4j.LoggerFactory
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.romanow.serialization.generated.ProtobufObjectProto
import ru.romanow.serialization.services.*
import java.util.*

@ShellComponent
@ShellCommandGroup("Protobuf")
class ProtobufCommand {
    private var logger = LoggerFactory.getLogger(ProtobufCommand::class.java)

    @ShellMethod(key = ["protobuf"], value = "Serialization and deserialization to Google Protobuf")
    fun protobuf() {
        val testObject = ProtobufObjectProto.TestObject
            .newBuilder()
            .setCode(RandomUtils.nextInt(0, 100))
            .setMessage(RandomStringUtils.randomAlphabetic(10))
            .setStatus(ProtobufObjectProto.Status.FAIL)
            .setInnerData(ProtobufObjectProto.InnerData.newBuilder().setCode("123").setPriority(100).build())
            .build()
        logger.info("Serialize object '{}' to Protobuf", testObject)

        val serializedData = serialize(testObject)
        logger.info("{}", Base64.getEncoder().encodeToString(serializedData))

        val parsedObject = parseFrom(serializedData, ProtobufObjectProto.TestObject.parser())
        logger.info("\n{}", parsedObject)
    }

}