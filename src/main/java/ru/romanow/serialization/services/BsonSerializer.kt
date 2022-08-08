package ru.romanow.serialization.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.undercouch.bson4jackson.BsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BsonSerializer {
    private static final Logger logger = LoggerFactory.getLogger(BsonSerializer.class);

    public static byte[] toBson(Object object) {
        byte[] result = null;
        ObjectMapper mapper = new ObjectMapper(new BsonFactory());
        try {
            result = mapper.writeValueAsBytes(object);
        } catch (Exception exception) {
            logger.error("", exception);
        }

        return result;
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
