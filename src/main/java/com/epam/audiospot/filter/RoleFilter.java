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

@WebFilter( urlPatterns = { "/controller" })
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
        String command = request.getParameter(COMMAND_PARAM);

        HttpSession session = httpRequest.getSession(false);
        if(session==null){
            if(!command.equals("login") &&!command.equals("register")
                    &&!command.equals("logout")&&!command.equals("home")){
                logger.warn("Unauthorized access attempt to command " + command);
                httpRequest.getRequestDispatcher(Forward.LOGIN.getPath()).forward(request,response);
                return;
            }
        }else{
            User user = (User)session.getAttribute(USER_ATTR);
            if(user==null){
                if(!command.equals("login") &&!command.equals("register")
                        &&!command.equals("logout")&&!command.equals("home")){
                    logger.warn("Unauthorized access attempt to command " + command);
                    httpRequest.getRequestDispatcher(Forward.LOGIN.getPath()).forward(request,response);
                    return;
                }
            }else{
                Role userRole = user.getRole();
                CommandType commandType = CommandType.getCurrentCommand(command);
                if(!commandType.isAuthorized(userRole)){
                    logger.warn("Unauthorized access attempt by user "
                            + user.getLogin() + " to command " + command);
                    httpRequest.getRequestDispatcher(Forward.MAIN.getPath()).forward(request,response);
                    return;
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}