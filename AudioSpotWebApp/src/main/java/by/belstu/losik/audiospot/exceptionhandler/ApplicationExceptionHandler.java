package com.epam.javalab.travelagency.exceptionhandler;

import com.epam.javalab.travelagency.exception.EntityNotFoundException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.logging.Level;

@Log
@ControllerAdvice
public class ApplicationExceptionHandler {
    private static final String GENERIC_NO_HANDLING_EXC_MESSAGE = "No handler mapping found";

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleEntityNotFoundException(Exception exception) {
        log.log(Level.SEVERE, exception.getMessage(), exception);
        return getExceptionModelAndView(exception);
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(value = AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(Exception exception) {
        log.log(Level.SEVERE, exception.getMessage(), exception);
        return getExceptionModelAndView(exception);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ModelAndView handleNoMappingFound(Exception exception) {
        log.log(Level.SEVERE, exception.getMessage(), exception);
        return getExceptionModelAndView(new Exception(GENERIC_NO_HANDLING_EXC_MESSAGE));
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(Exception exception) {
        log.log(Level.SEVERE, exception.getMessage(), exception);
        return getExceptionModelAndView(exception);
    }

    private ModelAndView getExceptionModelAndView(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception", exception);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
