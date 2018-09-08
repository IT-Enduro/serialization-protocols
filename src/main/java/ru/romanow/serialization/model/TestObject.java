package ru.romanow.serialization.model;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by ronin on 09.09.16
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TestObject
        implements Serializable {
    private static final long serialVersionUID = 6993892048926972018L;

    protected String message;
    protected Integer code;
    protected Status status;
}
