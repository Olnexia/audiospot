package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.entity.AudioSet;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioSetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitAudioSetCommand implements Command {
    private static final String AUDIOSET_ID_ATTR = "audioSetId";
    private static final Logger logger = LogManager.getLogger(SubmitAudioSetCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String title = request.getParameter(AudioSet.TITLE_LABEL);
        String description = request.getParameter(AudioSet.DESCRIPTION_LABEL);
        String idParam = request.getParameter(AudioSet.ID_LABEL);
        Long id = (idParam==null)?null:Long.parseLong(request.getParameter(AudioSet.ID_LABEL));

        AudioSetService service = new AudioSetService();
        AudioSet audioSet = new AudioSet(id,title,description);
        service.saveAudioSet(audioSet);
        logger.info("New audioset with id " + audioSet.getId() + " saved");
        return CommandResult.redirect(Redirect.VIEW_AUDIOSET.getPath()
                + "&" + AUDIOSET_ID_ATTR + "=" + audioSet.getId());
    }
}
