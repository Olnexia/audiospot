package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.command.creator.CommandType;
import com.epam.audiospot.command.utils.QuoteEscape;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Genre;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import com.epam.audiospot.validator.complex.TrackComplexValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class SubmitTrackCommand implements Command {
    private static final String ALBUM_ID_PARAM = "albumId";
    private static final String ALBUM_ID_ATTR = "albumId";
    private static final String ADD_TRACK_MESSAGE_ATTR = "addTrackMessage";
    private static final Logger logger = LogManager.getLogger(SubmitTrackCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String title = request.getParameter(AudioTrack.TITLE_LABEL);
        Genre genre = Genre.valueOf(request.getParameter(AudioTrack.GENRE_LABEL).toUpperCase());
        String artistName = request.getParameter(AudioTrack.ARTIST_LABEL);
        String albumId = request.getParameter(AudioTrack.ALBUM_ID_LABEL);
        String priceParam = request.getParameter(AudioTrack.PRICE_LABEL);

        TrackComplexValidator validator = new TrackComplexValidator(artistName, title, priceParam);
        Optional <List <String>> validateMessages = validator.validate();
        if (validateMessages.isPresent()) {
            request.setAttribute(ADD_TRACK_MESSAGE_ATTR, validateMessages.get());
            String path;
            if (albumId == null) {
                path = Forward.ADD_TRACK.getPath();
            } else {
                request.setAttribute(ALBUM_ID_ATTR, albumId);
                Command viewAlbumCommand = CommandType.VIEW_ALBUM.getCommand();
                path = viewAlbumCommand.execute(request, response).getPage();
            }
            return CommandResult.forward(path);
        }

        QuoteEscape quoteEscape = new QuoteEscape();
        artistName = quoteEscape.escape(artistName);
        title = quoteEscape.escape(title);

        BigDecimal price = new BigDecimal(priceParam);
        AudioTrackService service = new AudioTrackService();
        AudioTrack track = service.buildTrack(title, price, genre, artistName, albumId);
        service.submitTrack(track);

        logger.info("New track with id " + track.getId() + " added.");
        return (albumId == null) ? CommandResult.redirect(Redirect.SHOW_TRACKS.getPath())
                : CommandResult.redirect(Redirect.VIEW_ALBUM.getPath()
                + "&" + ALBUM_ID_PARAM + "=" + albumId);
    }
}
