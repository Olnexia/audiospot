package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.repository.UserByRoleSpecification;
import com.epam.audiospot.repository.UserRepository;
import com.epam.audiospot.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowClientsCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        UserService userService = new UserService();
        List<User> clients = userService.findClients();
        request.setAttribute("clients",clients);
        return CommandResult.forward("/WEB-INF/pages/clients.jsp");
    }
}
