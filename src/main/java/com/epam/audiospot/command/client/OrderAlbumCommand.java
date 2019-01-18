package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.OrderService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderAlbumCommand implements Command {
    private static final String USER_ATTR = "user";
    private static final String ALBUM_ID_PARAM = "albumId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_ATTR);
        Long albumId = Long.parseLong(request.getParameter(ALBUM_ID_PARAM));

        OrderService service = new OrderService();
        service.orderAlbum(user.getId(),albumId);

        return CommandResult.redirect(Redirect.VIEW_ALBUM.getPath()
                                    + "&" + ALBUM_ID_PARAM + "=" + albumId);
    }
}
