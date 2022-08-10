package ru.romanow.serialization.model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlValue

@XmlAccessorType(XmlAccessType.FIELD)
data class InnerData(
    @XmlValue
    var code: String? = null,

    @XmlAttribute(name = "priority")
    var priority: Int? = null
)