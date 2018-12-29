package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Page;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String LOGIN_REQUEST_PARAMETER = "login";
    private static final String PASSWORD_REQUEST_PARAMETER = "password";
    private static final String USER_SESSION_PARAMETER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String login = request.getParameter(LOGIN_REQUEST_PARAMETER);
        String password = request.getParameter(PASSWORD_REQUEST_PARAMETER);

        UserService service = new UserService();
        CommandResult page;
        try{
            Optional<User> user = service.login(login,password);
            if(user.isPresent()){
                HttpSession session = request.getSession(true);
                request.setAttribute(USER_SESSION_PARAMETER,user.get());
                session.setAttribute(USER_SESSION_PARAMETER,user.get());
                page = CommandResult.forward(Page.MAIN.getPath());
            }else {
                page = CommandResult.forward(Page.LOGIN.getPath());
                //TODO add some message like wrong password or login
//            request.setAttribute("errorLoginPassMessage",
//                    MessageManager.getProperty("message.loginerror"));
//            page = ConfigurationManager.getProperty("path.page.login");
            }
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return page;
    }
}
