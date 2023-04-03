package ru.romanow.serialization.commands

import org.slf4j.LoggerFactory
import org.springframework.shell.standard.ShellCommandGroup
import org.springframework.shell.standard.ShellComponent
import org.springframework.shell.standard.ShellMethod
import ru.romanow.serialization.model.XmlTestObject
import ru.romanow.serialization.model.createXmlTestObject
import ru.romanow.serialization.services.*

@ShellComponent
@ShellCommandGroup("XML")
class XmlCommand {
    private var logger = LoggerFactory.getLogger(XmlCommand::class.java)

    @ShellMethod(key = ["xml"], value = "Serialization and deserialization to XML")
    fun xml() {
        var testObject = createXmlTestObject()
        logger.info("Serialize object '{}' to XML", testObject)

        val xml = toXml(testObject)
        logger.info("\n{}", xml)

        testObject = fromXml(xml, XmlTestObject::class.java)
        logger.info("{}", testObject)
    }

    @ShellMethod(key = ["xpath"], value = "Evaluate XPath")
    fun xpath() {
        val xml = readFromFile(XML_DATA_FILE)
        logger.info("Read XML from file:\n{}", xml)

        val result = xpath(xml, XPATH)
        logger.info("XPath '{}' evaluates '{}'", XPATH, result)
    }

    @ShellMethod(key = ["validate"], value = "Validate XML using XSD schema")
    fun validate() {
        val xml = readFromFile(XML_DATA_FILE)
        logger.info("Read XML from file:\n{}", xml)
        logger.info("XML validation result: {}", validate(xml))
    }

    private fun readFromFile(fileName: String): String =
        object {}.javaClass.getResource(fileName)!!.readText()

    companion object {
        private const val XML_DATA_FILE = "/data/xml-data.xml"
        private const val XPATH = "//key[text()='KEY1']"
    }
}