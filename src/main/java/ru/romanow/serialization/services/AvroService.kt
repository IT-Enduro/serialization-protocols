package ru.romanow.serialization.services

import org.apache.avro.Schema
import org.apache.avro.generic.GenericDatumReader
import org.apache.avro.io.DatumReader
import org.apache.avro.io.DatumWriter
import org.apache.avro.io.DecoderFactory
import org.apache.avro.io.EncoderFactory
import org.apache.avro.reflect.ReflectDatumReader
import org.apache.avro.reflect.ReflectDatumWriter
import java.io.ByteArrayOutputStream

fun avroToJson(data: Any, schema: Schema): String {
    ByteArrayOutputStream().use { stream ->
        val writer: DatumWriter<Any> = ReflectDatumWriter(schema)
        val jsonEncoder = EncoderFactory.get().jsonEncoder(schema, stream)
        writer.write(data, jsonEncoder)
        jsonEncoder.flush()
        return String(stream.toByteArray())
    }
}

fun <T> avroFromJson(json: String, schema: Schema, cls: Class<T>): T {
    val reader: DatumReader<T?> = ReflectDatumReader(cls)
    val jsonDecoder = DecoderFactory.get().jsonDecoder(schema, json)
    return reader.read(null, jsonDecoder)!!
}

fun <T> avroFromJson(json: String, schema: Schema): T {
    val reader: DatumReader<T?> = GenericDatumReader(schema)
    val jsonDecoder = DecoderFactory.get().jsonDecoder(schema, json)
    return reader.read(null, jsonDecoder)!!
}