package com.epam.audiospot.exception;

public class ServiceException extends Exception {
    public ServiceException(String message,Throwable e){
        super(message,e);
    }
}
