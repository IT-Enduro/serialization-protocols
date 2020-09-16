# Протоколы сериализации

## Символьные
1. [Json](src/main/java/ru/romanow/serialization/services/JsonService.java)
1. [XML](src/main/java/ru/romanow/serialization/services/XmlService.java)

## Бинарные
1. [Avro](src/main/java/ru/romanow/serialization/services/AvroService.java)
1. [Bson](src/main/java/ru/romanow/serialization/services/BsonService.java)
1. [Msgpack](src/main/java/ru/romanow/serialization/services/MsgpackService.java)
1. [Protobuf](src/main/java/ru/romanow/serialization/services/ProtobufService.java)

## Утилиты
1. [XPath](src/main/java/ru/romanow/serialization/services/XpathService.java)
1. XSD schema validation
1. JsonPath

## Сборка и запуск
1. Установить Java 11.
1. `chmod a+x gradlew`
1. `./gradlew clean build bootRun`
