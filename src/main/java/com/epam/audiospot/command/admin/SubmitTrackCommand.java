package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Genre;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

public class SubmitTrackCommand implements Command {
    private static final String ALBUM_ID_PARAM = "albumId";
    private static final Logger logger = LogManager.getLogger(SubmitTrackCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException{
        String title = request.getParameter(AudioTrack.TITLE_LABEL);
        BigDecimal price = new BigDecimal(request.getParameter(AudioTrack.PRICE_LABEL));
        Genre genre = Genre.valueOf(request.getParameter(AudioTrack.GENRE_LABEL).toUpperCase());
        String artistName = request.getParameter(AudioTrack.ARTIST_LABEL);
        String albumId = request.getParameter(AudioTrack.ALBUM_ID_LABEL);

        AudioTrackService service = new AudioTrackService();
        AudioTrack track = service.buildTrack(title,price,genre,artistName,albumId);
        service.submitTrack(track);

        logger.info("New track with id "+track.getId()+" added.");
        return (albumId==null)? CommandResult.redirect(Redirect.SHOW_TRACKS.getPath())
                                : CommandResult.redirect(Redirect.VIEW_ALBUM.getPath()
                + "&" + ALBUM_ID_PARAM + "=" + albumId);
    }
}
