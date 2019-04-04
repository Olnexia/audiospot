package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTracksCommand implements Command {
    private static final String USER_PARAM = "user";
    private static final String SEARCH_REQUEST_PARAM = "request";
    private static final String TRACKS_ATTR = "tracks";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER_PARAM);
        String searchRequest = request.getParameter(SEARCH_REQUEST_PARAM);

        AudioTrackService service = new AudioTrackService();
        List <AudioTrack> availableTracks = service.findAvailableTracks(user.getId(),searchRequest);

        request.setAttribute(TRACKS_ATTR, availableTracks);
        return CommandResult.forward(Forward.TRACKS.getPath());
    }
}
