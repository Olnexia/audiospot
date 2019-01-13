package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.command.factory.CommandType;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderTrackCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("user");
        Long trackId = Long.parseLong(request.getParameter("trackId"));

        OrderService service = new OrderService();
        try{
            service.orderTrack(user.getId(),trackId);
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return CommandResult.redirect(Redirect.SHOW_TRACKS.getPath());
    }
}
