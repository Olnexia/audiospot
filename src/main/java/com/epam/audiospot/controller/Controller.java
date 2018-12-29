package com.epam.audiospot.controller;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.factory.CommandFactory;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.exception.CommandCreationException;
import com.epam.audiospot.exception.CommandExecutionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final String COMMAND_PARAMETER = "command";

    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        processRequest(request,response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        try {
            String command = request.getParameter(COMMAND_PARAMETER);
            Command action = CommandFactory.create(command);
            CommandResult commandResult = action.execute(request, response);
            if (commandResult.isRedirect()) {
                response.sendRedirect(commandResult.getPage());
            } else {
                request.getRequestDispatcher(commandResult.getPage()).forward(request, response);
            }
        }catch (CommandCreationException | CommandExecutionException e) {
            throw new IllegalArgumentException(e.getMessage(),e); //Just to see
        }
    }

//    private void processLanguage(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
//        try {
//            String command = request.getParameter(COMMAND_PARAMETER);
//            Command action = CommandFactory.create(command);
//            CommandResult commandResult = action.execute(request, response);
//            request.getRequestDispatcher(commandResult.getPage()).forward(request, response);
//        }catch (CommandCreationException | CommandExecutionException e) {
//            throw new IllegalArgumentException(e.getMessage(),e); //Just to see
//        }
//    }

    private void dispath(HttpServletRequest request,HttpServletResponse response){

    }
}
