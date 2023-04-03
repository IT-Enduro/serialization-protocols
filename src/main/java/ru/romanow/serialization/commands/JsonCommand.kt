package ru.romanow.serialization.commands

import org.slf4j.LoggerFactory
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.romanow.serialization.model.NewTestObject
import ru.romanow.serialization.model.createTestObject
import ru.romanow.serialization.services.*

@ShellComponent
@ShellCommandGroup("JSON")
class JsonCommand {
    private var logger = LoggerFactory.getLogger(JsonCommand::class.java)

    @ShellMethod(key = ["json"], value = "Serialization and deserialization to JSON")
    fun json() {
        val testObject = createTestObject()
        logger.info("Serialize object '{}' to JSON", testObject)

        val json = toJson(testObject)
        logger.info("\n{}", json)

        val newTestObject = fromJson(json, NewTestObject::class.java)
        logger.info("Deserialized object from JSON '{}'", newTestObject)
    }

    @ShellMethod(key = ["json-path"], value = "Evaluate Json Path")
    fun jsonPath() {
        val json = readFromFile(JSON_FILE)
        logger.info("Read JSON from file:\n{}", json)

        val result = jsonPath(json, JSON_PATH)
        logger.info("JsonPath '{}' evaluates '{}'", JSON_PATH, result)
    }

    private fun readFromFile(fileName: String): String =
        object {}.javaClass.getResource(fileName)!!.readText()

    companion object {
        private const val JSON_FILE = "/data/json-data.json"
        private const val JSON_PATH = "$..[?(@.key == 'KEY1')].key"
    }
}