package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final Logger logger = LogManager.getLogger(LogoutCommand.class);
    private static final String USER_ATTR = "user";
    private static final String LOCALE = "lang";
    private static final String LOCALE_PARAM = "/?lang=";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        String locale = (String)session.getAttribute(LOCALE);
        User user = (User)session.getAttribute(USER_ATTR);
        session.invalidate();

        logger.info("Session ended for user " + user.getLogin());
        return (locale == null)
                ? CommandResult.redirect(response.encodeRedirectURL(request.getContextPath() + "/"))
                : CommandResult.redirect(LOCALE_PARAM + locale);
    }
}
