package ru.romanow.serialization.model

import java.io.Serializable

data class NewTestObject(val version: String?) : TestObject(), Serializable {

    override fun toString(): String {
        return "NewTestObject(version=$version, message=$message, code=$code, status=$status, innerData=$innerData, publicData=$publicData)"
    }
}
