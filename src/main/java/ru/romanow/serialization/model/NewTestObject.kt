package ru.romanow.serialization.model

import com.google.common.base.MoreObjects
import java.io.Serializable

data class NewTestObject(val version: String) : TestObject(), Serializable {

    override fun toString(): String {
        return MoreObjects.toStringHelper(this)
            .add("version", version)
            .add("message", message)
            .add("code", code)
            .add("status", status)
            .add("innerData", innerData)
            .add("publicData", publicData)
            .toString()
    }
}