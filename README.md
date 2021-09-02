# Протоколы сериализации

## Символьные
1. [Json](src/main/java/ru/romanow/serialization/services/JsonSerializer.java)
1. [XML](src/main/java/ru/romanow/serialization/services/XmlSerializer.java)

## Бинарные
1. [Avro](src/main/java/ru/romanow/serialization/services/AvroService.java)
1. [Bson](src/main/java/ru/romanow/serialization/services/BsonSerializer.java)
1. [Msgpack](src/main/java/ru/romanow/serialization/services/MsgpackSerializer.java)
1. [Protobuf](src/main/java/ru/romanow/serialization/services/ProtobufService.java)

## Утилиты
1. [XPath](src/main/java/ru/romanow/serialization/services/XPathService.java)
1. XSD schema validation
1. JsonPath

## Сборка и запуск
```shell
./gradlew clean build bootRun
```
