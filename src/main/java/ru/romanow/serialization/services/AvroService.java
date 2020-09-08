package ru.romanow.serialization.services;

import lombok.SneakyThrows;
import org.apache.avro.Schema;
import org.apache.avro.io.*;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

import java.io.ByteArrayOutputStream;

public final class AvroService {

    @SneakyThrows
    public static String toJson(Object object, Schema schema) {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            final DatumWriter<Object> writer = new ReflectDatumWriter<>(schema);
            final JsonEncoder jsonEncoder = EncoderFactory.get().jsonEncoder(schema, stream);
            writer.write(object, jsonEncoder);
            jsonEncoder.flush();
            return new String(stream.toByteArray());
        }
    }

    @SneakyThrows
    public static <T> T fromJson(String json, Schema schema, Class<T> cls) {
        final DatumReader<T> reader = new ReflectDatumReader<>(cls);
        final JsonDecoder jsonDecoder = DecoderFactory.get().jsonDecoder(schema, json);
        return reader.read(null, jsonDecoder);
    }
}
