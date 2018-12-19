package com.epam.audiospot.command;

import com.epam.audiospot.exception.CommandExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        HttpSession session = request.getSession(false);
        session.invalidate();   //ok?
        return CommandResult.forward("/WEB-INF/pages/login.jsp");
    }
}
