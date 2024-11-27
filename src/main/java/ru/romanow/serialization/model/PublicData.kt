package ru.romanow.serialization.model

import jakarta.xml.bind.annotation.XmlAccessType
import jakarta.xml.bind.annotation.XmlAccessorType
import jakarta.xml.bind.annotation.XmlElement

@XmlAccessorType(XmlAccessType.FIELD)
data class PublicData(
    @XmlElement(name = "key")
    var key: String? = null,

    @XmlElement(name = "data")
    var data: String? = null
)
