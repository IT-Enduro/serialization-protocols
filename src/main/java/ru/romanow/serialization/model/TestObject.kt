package ru.romanow.serialization.model

open class TestObject(
    open var message: String? = null,
    open var code: Int? = null,
    open var status: Status? = null,
    open var innerData: InnerData? = null,
    open var publicData: List<PublicData>? = null
) {
    override fun toString(): String {
        return "TestObject(message=$message, code=$code, status=$status, innerData=$innerData, publicData=$publicData)"
    }
}