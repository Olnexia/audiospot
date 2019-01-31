package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeCommand implements Command {
    private static final String USER_ATTR = "user";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        return (session.getAttribute(USER_ATTR) == null)
                ? CommandResult.forward(Forward.LOGIN.getPath())
                : CommandResult.forward(Forward.MAIN.getPath());
    }
}
