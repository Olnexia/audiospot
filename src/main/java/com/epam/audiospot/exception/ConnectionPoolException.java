package com.epam.audiospot.exception;

public class ConnectionPoolException extends RuntimeException {
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }
}
