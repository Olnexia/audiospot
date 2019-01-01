package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Page;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Genre;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class SubmitTrackCommand implements Command {
    private static final String ARTIST_LABEL = "artist";
    private static final Logger logger = LogManager.getLogger(SubmitTrackCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        String title = request.getParameter(AudioTrack.TITLE_LABEL);
        BigDecimal price = new BigDecimal(request.getParameter(AudioTrack.PRICE_LABEL));
        int releaseYear = Integer.parseInt(request.getParameter(AudioTrack.RELEASE_YEAR_LABEL));
        Genre genre = Genre.valueOf(request.getParameter(AudioTrack.GENRE_LABEL).toUpperCase());
        String artistName = request.getParameter(ARTIST_LABEL);

        AudioTrackService service = new AudioTrackService();
        try{
            AudioTrack track = service.buildTrack(title,price,releaseYear,genre,artistName);
            service.submitTrack(track);
            logger.info("New track with id "+track.getId()+" added.");
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }

        //TODO here need some message for success

        return CommandResult.forward(Page.ADD_TRACK.getPath());
    }
}
