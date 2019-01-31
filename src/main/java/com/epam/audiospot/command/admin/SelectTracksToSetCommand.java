package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectTracksToSetCommand implements Command {
    private static final String AUDIOSET_ID_PARAM = "audioSetId";
    private static final String TRACKS_ATTR = "tracks";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long audioSetId = Long.parseLong(request.getParameter(AUDIOSET_ID_PARAM));

        AudioTrackService service = new AudioTrackService();
        List <AudioTrack> availableTracks = service.findNotSettedTracks(audioSetId);

        request.setAttribute(TRACKS_ATTR, availableTracks);
        return CommandResult.forward(Forward.TRACKS.getPath());
    }
}
