package ru.romanow.serialization;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.romanow.serialization.model.Status;
import ru.romanow.serialization.model.TestObject;
import ru.romanow.serialization.services.JsonSerializer;

/**
 * Created by romanow on 02.09.16
 */
@SpringBootApplication
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        Application application = new Application();
        application.testJson();
    }

    private void testJson() {
        TestObject testObject = createTestObject();

        logger.info("Serialize object '{}' to JSON", testObject);
        String json = JsonSerializer.toJson(testObject);
        logger.info("{}", json);
        logger.info("{}", JsonSerializer.fromJson(json, TestObject.class));
    }

    private TestObject createTestObject() {
        return new TestObject()
                .setMessage(RandomStringUtils.randomAlphanumeric(10))
                .setCode(RandomUtils.nextInt(0, 100))
                .setStatus(Status.DONE);
    }
}