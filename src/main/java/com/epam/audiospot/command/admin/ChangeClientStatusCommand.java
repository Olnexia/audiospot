package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import by.belstu.losik.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeClientStatusCommand implements Command {
    private static final String USER_ID_PARAM = "userId";
    private static final Logger logger = LogManager.getLogger(ChangeClientStatusCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long userId = Long.parseLong(request.getParameter(USER_ID_PARAM));

        UserService service = new UserService();
        User user = service.findUser(userId);

        boolean status = user.isActive();
        user.setActive(!status);
        service.saveUser(user);
        if (user.isActive()) {
            logger.info("User with id " + user.getId() + " unblocked.");
        } else {
            logger.info("User with id " + user.getId() + " blocked.");
        }
        return CommandResult.redirect(Redirect.SHOW_CLIENTS.getPath());
    }
}
