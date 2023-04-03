package ru.romanow.serialization.model

import org.apache.commons.lang3.RandomStringUtils.randomAlphabetic
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.apache.commons.lang3.RandomUtils.nextInt

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

fun createTestObject() =
    TestObject(
        message = randomAlphanumeric(10),
        code = nextInt(0, 100),
        status = Status.DONE,
        innerData = buildInnerData(),
        publicData = listOf(buildPublicData(), buildPublicData())
    )

fun buildPublicData() =
    PublicData(randomAlphabetic(8), randomAlphanumeric(15))

fun buildInnerData() =
    InnerData(randomAlphanumeric(15), nextInt(10, 15))