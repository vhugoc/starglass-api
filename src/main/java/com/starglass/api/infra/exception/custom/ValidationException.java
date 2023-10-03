package com.starglass.api.infra.exception.custom;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

}
