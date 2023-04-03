package ru.romanow.serialization

import org.junit.jupiter.api.Test
import ru.romanow.serialization.commands.JsonCommand

class JsonTest {
    private val command = JsonCommand()

    @Test
    fun testJson() {
        command.json()
    }

    @Test
    fun testJsonPath() {
        command.jsonPath()
    }
}