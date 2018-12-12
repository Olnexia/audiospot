package com.epam.audiospot.command;

import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String USER_PARAMETER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {

        HttpSession session = request.getSession(true);
        String login = request.getParameter(LOGIN_PARAMETER);
        String password = request.getParameter(PASSWORD_PARAMETER);

        UserService service = new UserService();
        Optional<User> user;
        CommandResult page;
        user = service.login(login,password);
        if(user.isPresent()){
            request.setAttribute(USER_PARAMETER,user.get());
            session.setAttribute(USER_PARAMETER,user.get());
            page = CommandResult.forward("/WEB-INF/pages/main.jsp");
        }else {
            page = null;  //to be remove
//            request.setAttribute("errorLoginPassMessage",
//                    MessageManager.getProperty("message.loginerror"));
//            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
}
