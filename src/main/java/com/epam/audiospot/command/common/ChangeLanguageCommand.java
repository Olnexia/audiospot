package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Page;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private static final String LANGUAGE_REQUEST_PARAMETER = "lang";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter(LANGUAGE_REQUEST_PARAMETER);
        HttpSession session = request.getSession(true);
        session.setAttribute("lang",language);

        return (session.getAttribute("user")==null)
                ?CommandResult.forward(Page.LOGIN.getPath())
                :CommandResult.forward(Page.MAIN.getPath());
    }
}
