package ru.romanow.serialization.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by ronin on 10.09.16
 */
public class BsonSerializer {
    private static final Logger logger = LoggerFactory.getLogger(BsonSerializer.class);

    public static byte[] toBson(Object object) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper(new BsonFactory());
        try {
            mapper.writeValue(stream, object);
        } catch (Exception exception) {
            logger.error("", exception);
        }

        return stream.toByteArray();
    }

    public static <T> T fromBson(byte[] bson, Class<T> cls) {
        T result = null;
        ObjectMapper mapper = new ObjectMapper(new BsonFactory());
        try {
            result = mapper.readValue(bson, cls);
        } catch (Exception exception) {
            logger.error("", exception);
        }

        return result;
    }
}
