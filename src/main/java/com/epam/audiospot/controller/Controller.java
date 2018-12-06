package com.epam.audiospot.controller;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandFactory;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.exception.CommandCreationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final String TARGET_PARAMETER = "command";

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
            String command = request.getParameter(TARGET_PARAMETER);
            Command action = CommandFactory.create(command);
            CommandResult commandResult = action.execute(request, response);
            request.getRequestDispatcher(commandResult.getPage()).forward(request, response);
        }catch (CommandCreationException e) {
            //logging?
        }
    }

    private void dispath(HttpServletRequest request,HttpServletResponse response){

    }
}
