package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 *
 */
public class LoginCommand implements Command {
    private static final String LOGIN_PARAM = "login";
    private static final String PASSWORD_PARAM = "password";
    private static final String LOCALE ="lang";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String login = request.getParameter(LOGIN_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);


        CommandResult commandResult;
        try{
            UserService service = new UserService();
            Optional<User> userOptional = service.login(login,password);
            if(userOptional.isPresent()){
                User user = userOptional.get();
                if(user.isActive()){
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user",user);
                    String currentLocale = request.getParameter(LOCALE);
                    session.setAttribute(LOCALE,currentLocale);
                    commandResult = CommandResult.redirect(Redirect.HOME.getPath());
                }else{
                    request.setAttribute("loginMessage","blocked");
                    commandResult = CommandResult.forward(Forward.LOGIN.getPath());
                }
            }else {
                commandResult = CommandResult.forward(Forward.LOGIN.getPath());
                request.setAttribute("loginMessage","wrongInput");
            }
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return commandResult;
    }
}
