package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowClientsCommand implements Command {
    private static final String CLIENTS_ATTR = "clients";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        UserService userService = new UserService();

        List<User> clients = userService.findClients();
        request.setAttribute(CLIENTS_ATTR,clients);

        return CommandResult.forward(Forward.CLIENTS.getPath());
    }
}
