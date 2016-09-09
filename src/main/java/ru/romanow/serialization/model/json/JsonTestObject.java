package ru.romanow.serialization.model.json;

import com.google.common.base.MoreObjects;
import ru.romanow.serialization.model.Status;
import ru.romanow.serialization.model.TestObject;

import java.io.Serializable;

/**
 * Created by ronin on 09.09.16
 */
public class JsonTestObject
        extends TestObject
        implements Serializable {
    private static final long serialVersionUID = 6993892048926972018L;

    public String getMessage() {
        return message;
    }

    public JsonTestObject setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public JsonTestObject setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Status getStatus() {
        return status;
    }

    public JsonTestObject setStatus(Status status) {
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
