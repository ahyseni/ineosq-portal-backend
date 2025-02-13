package com.ineos.management.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public abstract class IneosException extends Exception {

    private final IneosError mrktpError;

    public IneosException(String message) {
        super(message);
        this.mrktpError = null;
    }

    public IneosException(IneosError mrktpError) {
        this.mrktpError = mrktpError;
    }
}
