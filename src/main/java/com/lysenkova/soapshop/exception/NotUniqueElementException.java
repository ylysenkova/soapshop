package com.lysenkova.soapshop.exception;

public class NotUniqueElementException extends RuntimeException {
    public NotUniqueElementException(String message) {
        super(message);
    }

    public NotUniqueElementException(String message, Throwable cause) {
        super(message, cause);
    }
}
