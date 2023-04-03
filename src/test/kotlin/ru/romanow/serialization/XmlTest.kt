package ru.romanow.serialization

import org.junit.jupiter.api.Test
import ru.romanow.serialization.commands.XmlCommand

class XmlTest {
    private val command = XmlCommand()

    @Test
    fun testXml() {
        command.xml()
    }

    @Test
    fun testXPath() {
        command.xpath()
    }

    @Test
    fun testValidate() {
        command.validate()
    }
}