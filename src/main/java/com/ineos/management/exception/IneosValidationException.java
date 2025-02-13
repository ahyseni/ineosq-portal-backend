package com.ineos.management.exception;

public class IneosValidationException extends IneosException {
    IneosError mrktpError;
    String message;

    public IneosValidationException(String message) {
        super(message);
    }


}