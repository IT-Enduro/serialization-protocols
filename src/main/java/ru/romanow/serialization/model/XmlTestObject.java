package ru.romanow.serialization.model;

import com.google.common.base.MoreObjects;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by ronin on 09.09.16
 */
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

    public String getMessage() {
        return message;
    }

    public XmlTestObject setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public XmlTestObject setCode(Integer code) {
        this.code = code;
        return this;
    }

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
