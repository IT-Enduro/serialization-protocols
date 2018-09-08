package ru.romanow.serialization.model;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlTestObject
        extends TestObject
        implements Serializable {
    private static final long serialVersionUID = 632112641149051390L;

    @XmlElement(name = "message")
    protected String message;

    @XmlElement(name = "code")
    protected Integer code;

    @XmlElement(name = "status")
    protected Status status;
}
