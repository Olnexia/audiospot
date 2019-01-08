package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowTracksCommand implements Command {
    private static final String USER_SESSION_PARAMETER = "user";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        AudioTrackService service = new AudioTrackService();
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute(USER_SESSION_PARAMETER);
        try {
            List<AudioTrack> availableTracks = service.findAvailableTracks(user.getId());
            request.setAttribute("tracks",availableTracks);
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
        return CommandResult.forward(Forward.TRACKS.getPath());
    }
}
