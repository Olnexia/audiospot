package com.epam.audiospot.command;

import com.epam.audiospot.exception.CommandExecutionException;
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
     * @throws CommandExecutionException
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException;
}
