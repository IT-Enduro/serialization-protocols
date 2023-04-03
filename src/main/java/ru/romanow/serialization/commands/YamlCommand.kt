package ru.romanow.serialization.commands

import org.slf4j.LoggerFactory
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.romanow.serialization.model.TestObject
import ru.romanow.serialization.model.createTestObject
import ru.romanow.serialization.services.fromFile
import ru.romanow.serialization.services.fromYaml
import ru.romanow.serialization.services.toYaml

@ShellComponent
@ShellCommandGroup("YAML")
class YamlCommand {
    private var logger = LoggerFactory.getLogger(YamlCommand::class.java)

    @ShellMethod(key = ["yaml"], value = "Serialization and deserialization to YAML")
    fun yaml() {
        var testObject = createTestObject()
        logger.info("Serialize object '{}' to YAML", testObject)

        val yaml = toYaml(testObject)
        logger.info("\n{}", yaml)

        testObject = fromYaml(yaml, TestObject::class.java)
        logger.info("Deserialized object from YAML '{}'", testObject)
    }

    @ShellMethod(key = ["anchor"], value = "Read YAML with anchors to Java object")
    fun anchor() {
        val yaml = readFromFile(YAML_FILE)
        logger.info("Read YAML from file\n{}", yaml)

        val testObject = fromFile(yaml, TestObject::class.java)
        logger.info("Deserialized object from YAML '{}'", testObject)
    }

    private fun readFromFile(fileName: String): String =
        object {}.javaClass.getResource(fileName)!!.readText()

    companion object {
        private const val YAML_FILE = "/data/yaml-data.yml"
    }
}