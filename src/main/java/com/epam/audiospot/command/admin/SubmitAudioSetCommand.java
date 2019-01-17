package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.entity.AudioSet;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioSetService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubmitAudioSetCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SubmitAudioSetCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter(AudioSet.TITLE_LABEL);
        String description = request.getParameter(AudioSet.DESCRIPTION_LABEL);

        AudioSetService service = new AudioSetService();
        CommandResult commandResult;
        try{
            AudioSet audioSet = new AudioSet(null,title,description);
            service.saveAudioSet(audioSet);
            logger.info("New audioset with id "+audioSet.getId()+" added.");
            HttpSession session = request.getSession(false);
            session.setAttribute("audioSetId",audioSet.getId());
            commandResult = CommandResult.redirect(Redirect.VIEW_AUDIOSET.getPath()+"&audioSetId="+audioSet.getId());
        }catch (ServiceException e){
            logger.error("An error occurred while adding audioset: ",e);
            commandResult = CommandResult.forward(Forward.ADD_SET.getPath());
        }
        return commandResult;
    }
}
