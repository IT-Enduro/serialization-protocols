package ru.romanow.serialization.model

import java.io.Serializable
import javax.xml.bind.annotation.*

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
) : TestObject(), Serializable