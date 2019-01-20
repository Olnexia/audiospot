package com.epam.audiospot.command;

import com.epam.audiospot.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface is basis of any command in the application. The method {@code execute} should contain code of
 * the processing of the parameters passed in the http request.
 */
public interface Command {
    /**
     * @param request http request from client
     * @param response http response
     * @return instance of {@code CommandResult} class, which is stands for target for forward or redirect from command
     * @throws ServiceException if some unforeseen circumstances arose in the code of service methods
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
