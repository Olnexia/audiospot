package com.epam.audiospot.exception;

public class ConnectionException extends Exception {
    public ConnectionException(String message,Throwable cause){
        super(message,cause);
    }
}
