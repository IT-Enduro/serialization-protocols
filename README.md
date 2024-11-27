[![Build project](https://github.com/Romanow/serialization-protocols/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/Romanow/serialization-protocols/actions/workflows/build.yml)
[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-nc-nd/4.0/)
[![pre-commit](https://img.shields.io/badge/pre--commit-enabled-brightgreen?logo=pre-commit)](https://github.com/pre-commit/pre-commit)

# Протоколы сериализации

## Символьные

1. [Json](src/main/java/ru/romanow/serialization/services/JsonSerializer.kt)
2. [XML](src/main/java/ru/romanow/serialization/services/XmlSerializer.kt)
3. [YAML](src/main/java/ru/romanow/serialization/services/YamlSerializer.kt)

## Бинарные

1. [Avro](src/main/java/ru/romanow/serialization/services/AvroSerializer.kt)
2. [Protobuf](src/main/java/ru/romanow/serialization/services/ProtobufSerializer.kt)

## Утилиты

1. [XPath](src/main/java/ru/romanow/serialization/services/XmlSerializer.kt)
2. [XSD schema validation](src/main/java/ru/romanow/serialization/services/XmlSerializer.kt)
3. [JsonPath](src/main/java/ru/romanow/serialization/services/JsonSerializer.kt)

## Сборка и запуск

```shell
$ ./gradlew clean build

# запуск через `java -jar ...` потому что используется Spring Shell
$ java -jar build/libs/serialization-protocols.jar
```

## Ссылки

1. [YAML anchors](https://support.atlassian.com/bitbucket-cloud/docs/yaml-anchors/)
