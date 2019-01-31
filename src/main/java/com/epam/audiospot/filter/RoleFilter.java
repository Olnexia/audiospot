package com.epam.audiospot.filter;

import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.creator.CommandType;
import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = {"/controller"})
public class RoleFilter implements Filter {
    private static final String COMMAND_PARAM = "command";
    private static final String USER_ATTR = "user";
    private static final Logger logger = LogManager.getLogger(RoleFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (checkAuthorization(httpRequest)) {
            chain.doFilter(request, response);
        } else {
            String command = request.getParameter(COMMAND_PARAM);
            Optional <User> userOptional = getUserFromSession(httpRequest);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                logger.warn("Unauthorized access attempt by user "
                        + user.getLogin() + " to command " + command);
            } else {
                logger.warn("Unauthorized access attempt to command " + command);
            }
            httpRequest.getRequestDispatcher(Forward.LOGIN.getPath()).forward(request, response);
        }
    }

    private boolean checkAuthorization(HttpServletRequest request) {
        String commandParamValue = request.getParameter(COMMAND_PARAM);
        CommandType commandType = CommandType.getCurrentCommand(commandParamValue);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return commandType.isAvailableWithoutAuthorization();
        }

        Optional <User> userOptional = getUserFromSession(request);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Role userRole = user.getRole();
            return commandType.isAuthorized(userRole);
        } else {
            return commandType.isAvailableWithoutAuthorization();
        }
    }

    private Optional <User> getUserFromSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER_ATTR);
        return (user == null) ? Optional.empty() : Optional.of(user);
    }

    @Override
    public void destroy() {
    }
}