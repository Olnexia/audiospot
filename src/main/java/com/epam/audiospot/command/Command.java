package com.epam.audiospot.command;

import com.epam.audiospot.exception.ServiceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
public interface Command {
    /**
     *
     * @param request
     * @param response
     * @return
     * @throws ServiceException
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
