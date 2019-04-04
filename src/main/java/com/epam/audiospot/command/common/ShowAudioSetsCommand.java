package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import by.belstu.losik.audiospot.entity.AudioSet;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioSetService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAudioSetsCommand implements Command {
    private static final String AUDIOSETS_ATTR = "audioSets";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        AudioSetService service = new AudioSetService();
        List <AudioSet> audioSets = service.findAllAudioSets();

        request.setAttribute(AUDIOSETS_ATTR, audioSets);
        return CommandResult.forward(Forward.AUDIOSETS.getPath());
    }
}
