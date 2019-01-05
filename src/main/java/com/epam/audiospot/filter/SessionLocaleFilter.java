package com.epam.audiospot.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "SessionLocaleFilter", urlPatterns = {"/*"})
public class SessionLocaleFilter implements Filter {
    private static final String LOCALE = "lang";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        if (httpRequest.getParameter(LOCALE) != null) {
            HttpSession session = httpRequest.getSession(true);
            String currentLocale = httpRequest.getParameter(LOCALE);
            session.setAttribute(LOCALE, currentLocale);
        }
        chain.doFilter(request, response);
    }
    public void destroy() {

    }
    public void init(FilterConfig arg0){

    }
}
