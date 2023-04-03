package ru.romanow.serialization

import org.junit.jupiter.api.Test
import ru.romanow.serialization.commands.AvroCommand

class AvroTest {
    private val command = AvroCommand()

    @Test
    fun testGenerated() {
        command.generated()
    }

    @Test
    fun testManual() {
        command.manual()
    }
}