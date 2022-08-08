package ru.romanow.serialization.model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlValue

@XmlAccessorType(XmlAccessType.FIELD)
data class InnerData(
    @XmlValue
    private val code: String? = null,

    @XmlAttribute(name = "priority")
    private val priority: Int? = null
)