package ru.romanow.serialization.services

import com.fasterxml.jackson.databind.ObjectMapper
import de.undercouch.bson4jackson.BsonFactory

fun toBson(data: Any): ByteArray {
    val mapper = ObjectMapper(BsonFactory())
    return mapper.writeValueAsBytes(data)
}

fun <T> fromBson(bson: ByteArray, cls: Class<T>): T {
    val mapper = ObjectMapper(BsonFactory())
    return mapper.readValue(bson, cls)
}