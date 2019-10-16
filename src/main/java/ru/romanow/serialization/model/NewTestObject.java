package ru.romanow.serialization.model;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class NewTestObject
        extends TestObject
        implements Serializable {
    private static final long serialVersionUID = -5937186496112650685L;

    private String version;

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("version", version)
                .add("message", message)
                .add("code", code)
                .add("status", status)
                .add("innerData", innerData)
                .add("publicData", publicData)
                .toString();
    }
}
