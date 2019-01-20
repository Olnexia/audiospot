package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioSetService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTrackToSetCommand implements Command {
    private static final String TRACK_ID_PARAM = "trackId";
    private static final String AUDIOSET_ID_PARAM = "audioSetId";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long trackId = Long.parseLong(request.getParameter(TRACK_ID_PARAM));
        Long audioSetId = Long.parseLong(request.getParameter(AUDIOSET_ID_PARAM));

        AudioSetService service = new AudioSetService();
        service.addToSet(audioSetId,trackId);

        return CommandResult.redirect(Redirect.VIEW_AUDIOSET.getPath()
                                        + "&" + AUDIOSET_ID_PARAM
                                        + "=" + audioSetId);
    }
}
