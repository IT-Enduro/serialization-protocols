package ru.romanow.serialization.model;

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
}
