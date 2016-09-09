package ru.romanow.serialization.model.xml;

import com.google.common.base.MoreObjects;
import ru.romanow.serialization.model.Status;
import ru.romanow.serialization.model.TestObject;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by ronin on 09.09.16
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class XmlTestObject
        extends TestObject
        implements Serializable {
    private static final long serialVersionUID = 632112641149051390L;

    @XmlElement(name = "message")
    public String getMessage() {
        return message;
    }

    public XmlTestObject setMessage(String message) {
        this.message = message;
        return this;
    }

    @XmlElement(name = "code")
    public Integer getCode() {
        return code;
    }

    public XmlTestObject setCode(Integer code) {
        this.code = code;
        return this;
    }

    @XmlElement(name = "status")
    public Status getStatus() {
        return status;
    }

    public XmlTestObject setStatus(Status status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("message", message)
                          .add("code", code)
                          .add("status", status)
                          .toString();
    }
}
