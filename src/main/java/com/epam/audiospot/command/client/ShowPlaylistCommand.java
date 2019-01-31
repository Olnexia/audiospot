package com.epam.audiospot.command.client;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowPlaylistCommand implements Command {
    private static final String USER_PARAM = "user";
    private static final String TRACKS_ATTR = "tracks";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute(USER_PARAM);

        AudioTrackService service = new AudioTrackService();
        List <AudioTrack> orderedTracks = service.findTracksAtPlaylist(user.getId());

        request.setAttribute(TRACKS_ATTR, orderedTracks);
        return CommandResult.forward(Forward.PLAYLIST.getPath());
    }
}
