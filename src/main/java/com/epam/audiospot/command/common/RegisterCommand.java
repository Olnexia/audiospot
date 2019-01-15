package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");

        UserService service = new UserService();
        try{
            if(!service.isLoginAvailable(login)){
                request.setAttribute("registrationMessage","occupiedLogin");
                return CommandResult.forward(Forward.LOGIN.getPath());
            }
            String md5Password = DigestUtils.md5Hex(password).toUpperCase();
            User newClient = User.client(login,name,surname,md5Password);
            service.saveUser(newClient);

            HttpSession session = request.getSession(true);
            session.setAttribute("user",newClient);
            String currentLocale = request.getParameter("lang");
            session.setAttribute("lang",currentLocale);
            return CommandResult.redirect(Redirect.HOME.getPath());
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
    }
}
