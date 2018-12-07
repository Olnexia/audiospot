package com.epam.audiospot.exception;

public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String message,Throwable cause){
        super(message,cause);
    }
}
