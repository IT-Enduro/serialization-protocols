package ru.romanow.serialization.services;

import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;

public final class ProtobufService {

    @SneakyThrows
    public static <T extends MessageLite> byte[] serialize(T object) {
        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            object.writeTo(stream);
            return stream.toByteArray();
        }
    }

    @SneakyThrows
    public static <T extends GeneratedMessageV3> T parseFrom(byte[] serializedData, Parser<T> parser) {
        return parser.parseFrom(serializedData);
    }
}
