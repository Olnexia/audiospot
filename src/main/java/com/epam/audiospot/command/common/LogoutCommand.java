package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final String LANGUAGE_SESSION_ATTRIBUTE = "lang";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session!=null){
            Object language = session.getAttribute(LANGUAGE_SESSION_ATTRIBUTE);
            session.invalidate();
            request.setAttribute(LANGUAGE_SESSION_ATTRIBUTE,language);
        }
        return CommandResult.forward(Forward.LOGIN.getPath());
    }
}
