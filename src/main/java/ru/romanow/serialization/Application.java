package ru.romanow.serialization;

import ch.qos.logback.core.util.CloseUtil;
import com.google.common.io.Closeables;
import com.google.protobuf.InvalidProtocolBufferException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.Base64Utils;
import ru.romanow.serialization.generated.ProtobufObjectProto;
import ru.romanow.serialization.model.NewTestObject;
import ru.romanow.serialization.model.Status;
import ru.romanow.serialization.model.TestObject;
import ru.romanow.serialization.model.XmlTestObject;
import ru.romanow.serialization.services.BsonSerializer;
import ru.romanow.serialization.services.JsonSerializer;
import ru.romanow.serialization.services.MsgpackSerializer;
import ru.romanow.serialization.services.XmlSerializer;

import java.io.*;
import java.util.stream.Collectors;

/**
 * Created by romanow on 02.09.16
 */
@SpringBootApplication
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        Application application = new Application();
//        application.testJson();
//        application.testXml();
//        application.validateXml();
//        application.testBson();
//        application.testMsgPack();
        application.testProtobuf();
    }

    private void testProtobuf() throws UnsupportedEncodingException {
        ProtobufObjectProto.ProtobufObject testObject = ProtobufObjectProto
                .ProtobufObject
                .newBuilder()
                .setCode(RandomUtils.nextInt(0, 100))
                .setMessage(RandomStringUtils.randomAlphabetic(10))
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
            ProtobufObjectProto.ProtobufObject parsedObject =
                    ProtobufObjectProto.ProtobufObject.parseFrom(object);
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

    private void testXml() throws Exception {
        TestObject testObject = createXmlTestObject();
        logger.info("Serialize object '{}' to XML", testObject);

        String xml = XmlSerializer.toXml(testObject);
        logger.info("\n{}", xml);

        XmlTestObject newObject = XmlSerializer.fromXml(xml);
        logger.info("{}", newObject);
    }

    private void validateXml() {
        InputStream stream = ClassLoader.class.getResourceAsStream("/xml/data.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String xml = reader.lines().collect(Collectors.joining("\n"));
        Closeables.closeQuietly(reader);
        Closeables.closeQuietly(stream);

        logger.info("Read XML from file:\n{}", xml);

        logger.info("XML valid: {}", XmlSerializer.validate(xml));
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
                .setMessage(RandomStringUtils.randomAlphanumeric(10))
                .setCode(RandomUtils.nextInt(0, 100))
                .setStatus(Status.DONE);
    }

    private XmlTestObject createXmlTestObject() {
        return new XmlTestObject()
                .setMessage(RandomStringUtils.randomAlphanumeric(10))
                .setCode(RandomUtils.nextInt(0, 100))
                .setStatus(Status.DONE);
    }
}