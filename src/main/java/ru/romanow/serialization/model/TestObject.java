package ru.romanow.serialization.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TestObject {
    protected String message;
    protected Integer code;
    protected Status status;
    protected InnerData innerData;
    protected List<PublicData> publicData;
}
