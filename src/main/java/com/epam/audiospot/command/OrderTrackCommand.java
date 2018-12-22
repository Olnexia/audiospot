package com.epam.audiospot.command;

import com.epam.audiospot.command.client.BuyTracksCommand;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class OrderTrackCommand implements Command {
    private static final String USER_SESSION_PARAMETER = "user";
    private static final String TRACK_ID_REQUEST_PARAMETER = "trackId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_SESSION_PARAMETER);
        Long trackId = Long.parseLong(request.getParameter(TRACK_ID_REQUEST_PARAMETER));
        OrderService service = new OrderService();
        try{
            service.orderTrack(user.getId(),trackId);
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return CommandType.BUY_TRACKS.getCommand().execute(request,response);
    }
}
