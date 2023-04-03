package ru.romanow.serialization.model

import jakarta.xml.bind.annotation.*
import org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric
import org.apache.commons.lang3.RandomUtils
import java.io.Serializable

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
open class XmlTestObject(
    @XmlElement(name = "message")
    override var message: String? = null,

    @XmlElement(name = "code")
    override var code: Int? = null,

    @XmlElement(name = "status")
    override var status: Status? = null,

    @XmlElement(name = "innerData")
    override var innerData: InnerData? = null,

    @XmlElementWrapper(name = "publicDataList")
    override var publicData: List<PublicData>? = null
) : TestObject(), Serializable {

    override fun toString(): String {
        return "XmlTestObject(message=$message, code=$code, status=$status, innerData=$innerData, publicData=$publicData)"
    }
}

fun createXmlTestObject() =
    XmlTestObject(
        message = randomAlphanumeric(10),
        code = RandomUtils.nextInt(0, 100),
        status = Status.DONE,
        innerData = buildInnerData(),
        publicData = listOf(buildPublicData(), buildPublicData())
    )