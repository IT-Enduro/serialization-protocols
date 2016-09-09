package ru.romanow.serialization.model.json;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Created by ronin on 09.09.16
 */
public class NewJsonTestObject
        extends JsonTestObject
        implements Serializable {
    private static final long serialVersionUID = -5937186496112650685L;

    private String version;

    public String getVersion() {
        return version;
    }

    public NewJsonTestObject setVersion(String version) {
        this.version = version;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("message", message)
                          .add("code", code)
                          .add("status", status)
                          .add("version", version)
                          .toString();
    }
}
