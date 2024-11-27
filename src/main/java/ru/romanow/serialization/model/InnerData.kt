package ru.romanow.serialization.model

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlAttribute
import jakarta.xml.bind.annotation.XmlValue

@XmlAccessorType(XmlAccessType.FIELD)
data class InnerData(
    @XmlValue
    var code: String? = null,

    @XmlAttribute(name = "priority")
    var priority: Int? = null
)
