package com.epam.audiospot.exception;

public class IllegalBuildStateException extends RuntimeException {
    public IllegalBuildStateException(String message){
        super(message);
    }
}
