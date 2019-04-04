package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.Redirect;
import by.belstu.losik.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;
import com.epam.audiospot.validator.complex.ComplexValidator;
import com.epam.audiospot.validator.complex.RegistrationComplexValidator;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public class RegisterCommand implements Command {
    private static final Logger logger = LogManager.getLogger(RegisterCommand.class);
    private static final String REGISTRATION_MESSAGE_ATTR = "registrationMessage";
    private static final String LOGIN_PARAM = "login";
    private static final String NAME_PARAM = "name";
    private static final String SURNAME_PARAM = "surname";
    private static final String PASSWORD_PARAM = "password";
    private static final String USER_ATTR = "user";
    private static final String LOCALE = "lang";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String login = request.getParameter(LOGIN_PARAM);
        String name = request.getParameter(NAME_PARAM);
        String surname = request.getParameter(SURNAME_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        ComplexValidator <String> validator = new RegistrationComplexValidator(login, name, surname, password);
        Optional <List <String>> validateMessages = validator.validate();
        if (validateMessages.isPresent()) {
            request.setAttribute(REGISTRATION_MESSAGE_ATTR, validateMessages.get());
            return CommandResult.forward(Forward.LOGIN.getPath());
        }

        UserService service = new UserService();
        if (!service.isLoginAvailable(login)) {
            request.setAttribute(REGISTRATION_MESSAGE_ATTR, "occupiedLogin");
            return CommandResult.forward(Forward.LOGIN.getPath());
        }

        String md5Password = DigestUtils.md5Hex(password).toUpperCase();
        User newClient = User.client(login, name, surname, md5Password);
        service.saveUser(newClient);

        HttpSession session = request.getSession(true);
        String currentLocale = request.getParameter(LOCALE);
        session.setAttribute(USER_ATTR, newClient);
        session.setAttribute(LOCALE, currentLocale);

        logger.info("Registration is successful for client " + newClient.getLogin());
        return CommandResult.redirect(Redirect.HOME.getPath());
    }
}
