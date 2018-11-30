package com.epam.audiospot.command;

import com.epam.audiospot.entity.User;
import com.epam.audiospot.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final String LOGIN_PARAMETER = "login";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String USER_PARAMETER = "user";
    private static final String TARGET_PAGE = "";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        session.setAttribute("login",new User("Olnexia","admin"));
        return CommandResult.forward("/WEB-INF/pages/main.jsp");

//        UserService service = new UserService();
//        String login = request.getParameter(LOGIN_PARAMETER);
//        String password = request.getParameter(PASSWORD_PARAMETER);
//        Optional<User> user = service.login(login,password);
//        user.ifPresent(u->request.setAttribute(USER_PARAMETER,u));
//        return TARGET_PAGE;
    }
}
