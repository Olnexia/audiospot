package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.command.utils.QuoteEscape;
import com.epam.audiospot.entity.Album;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AlbumService;
import com.epam.audiospot.validator.complex.AlbumComplexValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class SubmitAlbumCommand implements Command {
    private static final String ARTIST_PARAM = "artist";
    private static final String ALBUM_ID_ATTR = "albumId";
    private static final String ADD_ALBUM_MESSAGE_ATTR = "addAlbumMessage";
    private static final Logger logger = LogManager.getLogger(SubmitTrackCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String title = request.getParameter(Album.TITLE_LABEL);
        String yearParam = request.getParameter(Album.RELEASE_YEAR_LABEL);
        String artistName = request.getParameter(ARTIST_PARAM);

        AlbumComplexValidator validator = new AlbumComplexValidator(artistName,title,yearParam);
        Optional<List<String>> validateMessages = validator.validate();
        if(validateMessages.isPresent()){
            request.setAttribute(ADD_ALBUM_MESSAGE_ATTR, validateMessages.get());
            return CommandResult.forward(Forward.ADD_ALBUM.getPath());
        }

        QuoteEscape quoteEscape = new QuoteEscape();
        artistName = quoteEscape.escape(artistName);
        title = quoteEscape.escape(title);

        int releaseYear = Integer.parseInt(yearParam);

        AlbumService service = new AlbumService();
        Album album = service.buildAlbum(title,artistName,releaseYear);
        service.addAlbum(album);

        logger.info("New album with id " + album.getId() + " added.");
        return CommandResult.redirect(Redirect.VIEW_ALBUM.getPath()
                + "&" + ALBUM_ID_ATTR + "=" + album.getId());
    }
}
