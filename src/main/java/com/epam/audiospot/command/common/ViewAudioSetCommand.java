package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.creator.CommandType;
import by.belstu.losik.audiospot.entity.AudioSet;
import by.belstu.losik.audiospot.entity.AudioTrack;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioSetService;
import com.epam.audiospot.service.AudioTrackService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ViewAudioSetCommand implements Command {
    private static final String AUDIOSET_ID_PARAM = "audioSetId";
    private static final String AUDIOSET_ATTR = "audioSet";
    private static final String TRACKS_ATTR = "tracks";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long audioSetId = Long.parseLong(request.getParameter(AUDIOSET_ID_PARAM));

        AudioSetService audioSetService = new AudioSetService();
        Optional <AudioSet> audioSetOptional = audioSetService.findAudioSet(audioSetId);
        if (audioSetOptional.isPresent()) {
            AudioSet audioSet = audioSetOptional.get();
            request.setAttribute(AUDIOSET_ATTR, audioSet);
        } else {
            Command audioSetsCommand = CommandType.SHOW_AUDIOSETS.getCommand();
            String page = audioSetsCommand.execute(request, response).getPage();
            return CommandResult.forward(page);
        }

        AudioTrackService trackService = new AudioTrackService();
        List <AudioTrack> tracks = trackService.findTracksAtAudioSet(audioSetId);

        request.setAttribute(TRACKS_ATTR, tracks);
        return CommandResult.forward(Forward.VIEW_AUDIOSET.getPath());
    }
}
