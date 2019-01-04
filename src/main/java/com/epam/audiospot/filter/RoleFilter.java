package com.epam.audiospot.filter;

import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebFilter( urlPatterns = { "/controller" })
public class RoleFilter implements Filter {
    private static final String COMMAND_REQUEST_PARAMETER = "command";
    private static final String USER_SESSION_PARAMETER = "user";

    public void init(FilterConfig fConfig){
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String command = request.getParameter(COMMAND_REQUEST_PARAMETER);

        HttpSession session = httpRequest.getSession(false);
        if(session==null){
            httpRequest.getRequestDispatcher(Forward.LOGIN.getPath()).forward(request,response);
            return;
        }
        User user = (User)session.getAttribute(USER_SESSION_PARAMETER);
        if(user==null){
            if(!command.equals("login")&&!command.equals("changeLang")){
                httpRequest.getRequestDispatcher(Forward.LOGIN.getPath()).forward(request,response);
                return;
            }
        }else{
            Role userRole = user.getRole();
            List<String> permissions = userRole.getPermissions();
            if(!permissions.contains(command)){
                httpRequest.getRequestDispatcher(Forward.MAIN.getPath()).forward(request,response);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}