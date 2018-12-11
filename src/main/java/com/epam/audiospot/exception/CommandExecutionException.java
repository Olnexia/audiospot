package com.epam.audiospot.exception;

public class CommandExecutionException extends Exception {
    public CommandExecutionException(String message, Throwable cause){
        super(message,cause);
    }
}
