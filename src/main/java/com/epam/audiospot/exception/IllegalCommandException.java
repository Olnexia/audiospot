package com.epam.audiospot.exception;

public class IllegalCommandException extends RuntimeException {
    public IllegalCommandException(String message, Throwable cause){
        super(message,cause);
    }
}
