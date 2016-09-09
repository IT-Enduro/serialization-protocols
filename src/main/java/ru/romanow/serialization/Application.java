package ru.romanow.serialization;

import com.google.common.io.Closeables;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.romanow.serialization.model.Status;
import ru.romanow.serialization.model.TestObject;
import ru.romanow.serialization.model.json.JsonTestObject;
import ru.romanow.serialization.model.json.NewJsonTestObject;
import ru.romanow.serialization.model.xml.XmlTestObject;
import ru.romanow.serialization.services.JsonSerializer;
import ru.romanow.serialization.services.XmlSerializer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    private void testJson() {
        TestObject testObject = createJsonTestObject();

        logger.info("Serialize object '{}' to JSON", testObject);

        String json = JsonSerializer.toJson(testObject);
        logger.info("\n{}", json);

        NewJsonTestObject newObject = JsonSerializer.fromJson(json, NewJsonTestObject.class);
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

    private JsonTestObject createJsonTestObject() {
        return new JsonTestObject()
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