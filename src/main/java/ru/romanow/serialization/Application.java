package ru.romanow.serialization;

import ch.qos.logback.core.util.CloseUtil;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Base64Utils;
import ru.romanow.serialization.generated.ProtobufObjectProto;
import ru.romanow.serialization.model.*;
import ru.romanow.serialization.services.BsonSerializer;
import ru.romanow.serialization.services.JsonSerializer;
import ru.romanow.serialization.services.MsgpackSerializer;
import ru.romanow.serialization.services.XmlSerializer;

import java.io.*;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@SuppressWarnings("unused")
@SpringBootApplication
public class Application
        implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
//        testJson();
        testXml();
//        validateXml();
//        testBson();
//        testMsgPack();
//        testProtobuf();
    }

    private void testProtobuf() {
        ProtobufObjectProto.TestObject testObject = ProtobufObjectProto
                .TestObject
                .newBuilder()
                .setCode(nextInt(0, 100))
                .setMessage(randomAlphabetic(10))
                .setStatus(ProtobufObjectProto.Status.FAIL)
                .build();

        logger.info("Serialize object '{}' to Protobuf", testObject);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            testObject.writeTo(stream);
        } catch (IOException exception) {
            logger.error("", exception);
        }

        CloseUtil.closeQuietly(stream);
        byte[] object = stream.toByteArray();
        logger.info("{}", Base64Utils.encodeToString(object));

        try {
            ProtobufObjectProto.TestObject parsedObject =
                    ProtobufObjectProto.TestObject.parseFrom(object);
            logger.info("\n{}", parsedObject);
        } catch (InvalidProtocolBufferException exception) {
            logger.error("", exception);
        }
    }

    private void testJson() {
        TestObject testObject = createTestObject();

        logger.info("Serialize object '{}' to JSON", testObject);

        String json = JsonSerializer.toJson(testObject);
        logger.info("\n{}", json);

        NewTestObject newObject = JsonSerializer.fromJson(json, NewTestObject.class);
        logger.info("{}", newObject);
    }

    private void testXml() {
        TestObject testObject = createXmlTestObject();
        logger.info("Serialize object '{}' to XML", testObject);

        String xml = XmlSerializer.toXml(testObject);
        logger.info("\n{}", xml);

        XmlTestObject newObject = XmlSerializer.fromXml(xml);
        logger.info("{}", newObject);
    }

    private void validateXml() {
        try (InputStream stream = ClassLoader.class.getResourceAsStream("/xml/data.xml")) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
                String xml = reader.lines().collect(Collectors.joining("\n"));

                logger.info("Read XML from file:\n{}", xml);
                logger.info("XML valid: {}", XmlSerializer.validate(xml));
            }
        } catch (IOException exception) {
            logger.error("", exception);
        }
    }

    private void testBson() {
        TestObject testObject = createTestObject();

        logger.info("Serialize object '{}' to BSON", testObject);

        byte[] bson = BsonSerializer.toBson(testObject);
        logger.info("\n{}", Base64Utils.encodeToString(bson));

        TestObject newObject = BsonSerializer.fromBson(bson, TestObject.class);
        logger.info("{}", newObject);
    }

    private void testMsgPack() {
        TestObject testObject = createTestObject();

        logger.info("Serialize object '{}' to MsgPack", testObject);

        byte[] bson = MsgpackSerializer.toMsgpack(testObject);
        logger.info("\n{}", Base64Utils.encodeToString(bson));

        TestObject newObject = MsgpackSerializer.fromMsgpack(bson, TestObject.class);
        logger.info("{}", newObject);
    }

    private TestObject createTestObject() {
        return new TestObject()
                .setMessage(randomAlphanumeric(10))
                .setCode(nextInt(0, 100))
                .setStatus(Status.DONE)
                .setInnerData(buildInnerData())
                .setPublicData(newArrayList(buildPublicData(), buildPublicData()));
    }

    private XmlTestObject createXmlTestObject() {
        return new XmlTestObject()
                .setMessage(randomAlphanumeric(10))
                .setCode(nextInt(0, 100))
                .setStatus(Status.DONE)
                .setInnerData(buildInnerData())
                .setPublicData(newArrayList(buildPublicData(), buildPublicData()));
    }

    private PublicData buildPublicData() {
        return new PublicData(randomAlphabetic(8), randomAlphanumeric(15));
    }

    private InnerData buildInnerData() {
        return new InnerData(randomAlphanumeric(15), nextInt(10, 15));
    }
}