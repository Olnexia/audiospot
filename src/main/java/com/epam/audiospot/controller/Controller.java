package com.epam.audiospot.controller;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.factory.CommandFactory;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.exception.CommandExecutionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final String COMMAND_REQUEST_PARAMETER = "command";
    private static final Logger logger = LogManager.getLogger(Controller.class);

    public void init() throws ServletException {
        super.init();
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
            String command = request.getParameter(COMMAND_REQUEST_PARAMETER);
            Command action = CommandFactory.create(command);
            CommandResult commandResult = action.execute(request, response);
            if (commandResult.isRedirect()) {
                response.sendRedirect(commandResult.getPage());
            } else {
                request.getRequestDispatcher(commandResult.getPage()).forward(request, response);
            }
        }catch (CommandExecutionException | ServletException | IOException e) {
            logger.error(e.getMessage());
            throw new ServletException(e.getMessage());
        }
    }
}
