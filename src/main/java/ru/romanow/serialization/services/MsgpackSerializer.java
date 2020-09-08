package ru.romanow.serialization.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

public final class MsgpackSerializer {
    private static final Logger logger = LoggerFactory.getLogger(MsgpackSerializer.class);

    public static byte[] toMsgpack(Object object) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
        try {
            mapper.writeValue(stream, object);
        } catch (Exception exception) {
            logger.error("", exception);
        }

        return stream.toByteArray();
    }

    public static <T> T fromMsgpack(byte[] msg, Class<T> cls) {
        T result = null;
        ObjectMapper mapper = new ObjectMapper(new MessagePackFactory());
        try {
            result = mapper.readValue(msg, cls);
        } catch (Exception exception) {
            logger.error("", exception);
        }

        return result;
    }
}
