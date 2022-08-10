package ru.romanow.serialization.model

open class TestObject(
    open var message: String? = null,
    open var code: Int? = null,
    open var status: Status? = null,
    open var innerData: InnerData? = null,
    open var publicData: List<PublicData>? = null
)