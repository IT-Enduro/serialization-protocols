# Протоколы сериализации

## Символьные

1. [Json](src/main/java/ru/romanow/serialization/services/JsonSerializer.kt)
2. [XML](src/main/java/ru/romanow/serialization/services/XmlSerializer.kt)
3. [YAML](src/main/java/ru/romanow/serialization/services/YamlSerializer.kt)

### TODO

1. CSV

## Бинарные

1. [Avro](src/main/java/ru/romanow/serialization/services/AvroService.kt)
2. [Protobuf](src/main/java/ru/romanow/serialization/services/ProtobufService.kt)

## Утилиты

1. [XPath](src/main/java/ru/romanow/serialization/services/XPathService.kt)
2. XSD schema validation
3. JsonPath

## Сборка и запуск

```shell
$ ./gradlew clean build

# запуск через `java -jar ...` потому что используется Spring Shell 
$ java -jar build/libs/serialization-protocols.jar
```

## Ссылки

1. [YAML anchors](https://support.atlassian.com/bitbucket-cloud/docs/yaml-anchors/)