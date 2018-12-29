package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Page;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return CommandResult.forward(Page.LOGIN.getPath());
    }
}
