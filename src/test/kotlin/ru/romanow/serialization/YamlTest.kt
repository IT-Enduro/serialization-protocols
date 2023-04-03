package ru.romanow.serialization

import org.junit.jupiter.api.Test
import ru.romanow.serialization.commands.YamlCommand

class YamlTest {
    private val command = YamlCommand()

    @Test
    fun testYaml() {
        command.yaml()
    }

    @Test
    fun testAnchor() {
        command.anchor()
    }
}