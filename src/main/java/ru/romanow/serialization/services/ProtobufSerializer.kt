package ru.romanow.serialization.services

import com.google.protobuf.GeneratedMessageV3
import com.google.protobuf.MessageLite
import com.google.protobuf.Parser
import java.io.ByteArrayOutputStream

fun <T : MessageLite> serialize(data: T): ByteArray {
    ByteArrayOutputStream().use { stream ->
        data.writeTo(stream)
        return stream.toByteArray()
    }
}

fun <T : GeneratedMessageV3> parseFrom(serializedData: ByteArray, parser: Parser<T>): T {
    return parser.parseFrom(serializedData)
}