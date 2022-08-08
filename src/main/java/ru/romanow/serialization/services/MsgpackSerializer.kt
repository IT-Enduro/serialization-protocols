package ru.romanow.serialization.services

import com.fasterxml.jackson.databind.ObjectMapper
import org.msgpack.jackson.dataformat.MessagePackFactory
import java.io.ByteArrayOutputStream

fun toMsgpack(data: Any): ByteArray {
    val stream = ByteArrayOutputStream()
    val mapper = ObjectMapper(MessagePackFactory())
    mapper.writeValue(stream, data)
    return stream.toByteArray()
}

fun <T> fromMsgpack(msg: ByteArray, cls: Class<T>): T {
    val mapper = ObjectMapper(MessagePackFactory())
    return mapper.readValue(msg, cls)
}