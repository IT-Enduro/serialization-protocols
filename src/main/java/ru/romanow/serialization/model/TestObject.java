package ru.romanow.serialization.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Created by ronin on 09.09.16
 */
public class TestObject 
    implements Serializable {
    private static final long serialVersionUID = 6993892048926972018L;

    protected String message;
    protected Integer code;
    protected Status status;
    
    public String getMessage() {
        return message;
    }

    public TestObject setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public TestObject setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public TestObject setStatus(Status status) {
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
