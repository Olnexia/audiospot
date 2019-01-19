package com.epam.audiospot.controller;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.creator.CommandCreator;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.connection.ConnectionPool;
import com.epam.audiospot.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final String COMMAND_PARAM = "command";
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.info("The controller has been initialized");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        processRequest(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException{
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String command = request.getParameter(COMMAND_PARAM);
            Command action = CommandCreator.create(command);
            CommandResult commandResult = action.execute(request, response);
            if (commandResult.isRedirect()) {
                response.sendRedirect(commandResult.getPage());
            } else {
                request.getRequestDispatcher(commandResult.getPage()).forward(request, response);
            }
        }catch (ServiceException | ServletException | IOException e) {
            logger.error(e.getMessage(), e);
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool pool = ConnectionPool.getInstance();
        pool.closeConnections();
        super.destroy();
        logger.info("The controller has been destroyed");
    }
}
