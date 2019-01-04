package com.epam.audiospot.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if (httpRequest.getParameter("lang") != null) {
            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("lang", httpRequest.getParameter("lang"));
        }
        chain.doFilter(request, response);
    }

    public void destroy() {

    }

    public void init(FilterConfig arg0){

    }
}
