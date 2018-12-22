package com.epam.audiospot.command;

import com.epam.audiospot.exception.CommandExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        HttpSession session = request.getSession();
        if(session.getAttribute("user")==null){
            return CommandResult.forward("/WEB-INF/pages/login.jsp");

        }else {
            return CommandResult.forward("/WEB-INF/pages/main.jsp");
        }
    }
}
