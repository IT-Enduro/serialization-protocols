package ru.romanow.serialization.services

import com.google.gson.GsonBuilder

private val gson = GsonBuilder()
    .setPrettyPrinting()
    .create()

fun toJson(data: Any): String {
    return gson.toJson(data)
}

fun <T> fromJson(json: String, cls: Class<T>): T {
    return gson.fromJson(json, cls)
}