package ru.romanow.serialization.services

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper
import org.yaml.snakeyaml.Yaml

private var mapper = YAMLMapper()

fun toYaml(data: Any): String = mapper.writeValueAsString(data)

fun <T> fromYaml(yaml: String, cls: Class<T>): T = mapper.readValue(yaml, cls)

fun <T> fromFile(yaml: String, cls: Class<T>): T = Yaml().loadAs(yaml, cls)
