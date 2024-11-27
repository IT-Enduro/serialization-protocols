package ru.romanow.serialization

import org.junit.jupiter.api.Test
import ru.romanow.serialization.commands.ProtobufCommand

class ProtobufTest {
    private val command = ProtobufCommand()

    @Test
    fun testProtobuf() {
        command.protobuf()
    }
}
