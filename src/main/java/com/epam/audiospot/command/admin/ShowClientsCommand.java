package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowClientsCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        return CommandResult.forward("/WEB-INF/pages/clients.jsp");
    }
}
