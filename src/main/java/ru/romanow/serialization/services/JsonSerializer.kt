package ru.romanow.serialization.services

import com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT
import com.fasterxml.jackson.module.kotlin.jacksonMapperBuilder
import com.jayway.jsonpath.JsonPath

private val mapper = jacksonMapperBuilder()
    .configure(INDENT_OUTPUT, true)
    .build()

fun toJson(data: Any): String = mapper.writeValueAsString(data)

fun <T> fromJson(json: String, cls: Class<T>): T = mapper.readValue(json, cls)

fun jsonPath(json: String, path: String): List<String> = JsonPath.read<List<String>>(json, path)