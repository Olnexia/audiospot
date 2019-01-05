package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final String LOCALE = "lang";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if (session == null) {
            String currentLocale = request.getParameter(LOCALE);
            request.setAttribute(LOCALE, currentLocale);
        } else {
            Object locale = session.getAttribute(LOCALE);
            session.invalidate();
            request.setAttribute(LOCALE, locale);
        }
        return CommandResult.forward(Forward.LOGIN.getPath());
    }
}
