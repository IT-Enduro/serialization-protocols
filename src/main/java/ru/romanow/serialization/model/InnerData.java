package ru.romanow.serialization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class InnerData {

    @XmlValue
    private String code;

    @XmlAttribute(name = "priority")
    private Integer priority;
}
