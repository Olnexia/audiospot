package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import by.belstu.losik.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderTrackCommand implements Command {
    private static final String USER_ATTR = "user";
    private static final String TRACK_ID_PARAM = "trackId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER_ATTR);
        Long trackId = Long.parseLong(request.getParameter(TRACK_ID_PARAM));

        OrderService service = new OrderService();
        service.orderTrack(user.getId(), trackId);

        return CommandResult.redirect(Redirect.SHOW_TRACKS.getPath());
    }
}
