package com.epam.audiospot.command;

import com.epam.audiospot.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowPlaylistCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        session.setAttribute("login",new User("Olnexia","admin"));
        return CommandResult.forward("/WEB-INF/pages/main.jsp");
    }
}
