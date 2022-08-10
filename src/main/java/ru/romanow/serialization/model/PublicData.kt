package ru.romanow.serialization.model

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement

@XmlAccessorType(XmlAccessType.FIELD)
data class PublicData(
    @XmlElement(name = "key")
    var key: String? = null,

    @XmlElement(name = "data")
    var data: String? = null
)