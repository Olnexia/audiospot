package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.AudioSet;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioSetService;
import com.epam.audiospot.service.AudioTrackService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ViewAudioSetCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        Long audioSetId = Long.parseLong(request.getParameter("audioSetId"));
        AudioSetService audioSetService = new AudioSetService();
        AudioTrackService trackService = new AudioTrackService();
        try {
            Optional<AudioSet> audioSetOptional = audioSetService.findAudioSet(audioSetId);
            if(audioSetOptional.isPresent()){
                AudioSet audioSet = audioSetOptional.get();
                request.setAttribute("audioSet",audioSet);
            }
            List<AudioTrack> tracks = trackService.findTracksAtAudioSet(audioSetId);
            request.setAttribute("tracks",tracks);
            return CommandResult.forward(Forward.VIEW_AUDIOSET.getPath());
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
    }
}
