package com.epam.audiospot.controller;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandFactory;
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

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException,ServletException {
//        request.setAttribute("name",request.getParameter("name"));
//        request.getRequestDispatcher("/index.jsp").forward(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response){
        String command = request.getParameter(TARGET_PARAMETER);
        Command action = CommandFactory.create(command);
        String page = null;
        try{
            page = action.execute(request,response);
        }catch (){

        }
    }

    private void dispath(HttpServletRequest request,HttpServletResponse response){

    }
}
