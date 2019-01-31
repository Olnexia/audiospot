package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.command.utils.QuoteEscape;
import com.epam.audiospot.entity.AudioSet;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioSetService;
import com.epam.audiospot.validator.complex.AudioSetComplexValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class SubmitAudioSetCommand implements Command {
    private static final String AUDIOSET_ID_ATTR = "audioSetId";
    private static final String ADD_AUDIOSET_MESSAGE_ATTR = "addAudioSetMessage";
    private static final Logger logger = LogManager.getLogger(SubmitAudioSetCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String title = request.getParameter(AudioSet.TITLE_LABEL);
        String description = request.getParameter(AudioSet.DESCRIPTION_LABEL);
        String idParam = request.getParameter(AudioSet.ID_LABEL);
        Long id = (idParam == null) ? null : Long.parseLong(request.getParameter(AudioSet.ID_LABEL));

        AudioSetComplexValidator validator = new AudioSetComplexValidator(title, description);
        Optional <List <String>> validateMessages = validator.validate();
        if (validateMessages.isPresent()) {
            request.setAttribute(ADD_AUDIOSET_MESSAGE_ATTR, validateMessages.get());
            return CommandResult.forward(Forward.ADD_SET.getPath());
        }

        QuoteEscape quoteEscape = new QuoteEscape();
        title = quoteEscape.escape(title);
        description = quoteEscape.escape(description);

        AudioSetService service = new AudioSetService();
        AudioSet audioSet = new AudioSet(id, title, description);
        service.saveAudioSet(audioSet);
        logger.info("New audioset with id " + audioSet.getId() + " saved");
        return CommandResult.redirect(Redirect.VIEW_AUDIOSET.getPath()
                + "&" + AUDIOSET_ID_ATTR + "=" + audioSet.getId());
    }
}
