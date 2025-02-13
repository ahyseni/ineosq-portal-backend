package com.ineos.management.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class IneosSubError implements Serializable {

    private final String object;

    private final String field;

    private final Serializable rejectedValue;

    private final String message;
}
