package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.entity.Album;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AlbumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitAlbumCommand implements Command {
    private static final String ARTIST_PARAM = "artist";
    private static final String ALBUM_ID_ATTR = "albumId";
    private static final Logger logger = LogManager.getLogger(SubmitTrackCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String title = request.getParameter(Album.TITLE_LABEL);
        int releaseYear = Integer.parseInt(request.getParameter(Album.RELEASE_YEAR_LABEL));
        String artistName = request.getParameter(ARTIST_PARAM);

        AlbumService service = new AlbumService();
        Album album = service.buildAlbum(title,artistName,releaseYear);
        service.addAlbum(album);

        logger.info("New album with id " + album.getId() + " added.");
        return CommandResult.redirect(Redirect.VIEW_ALBUM.getPath()
                + "&" + ALBUM_ID_ATTR + "=" + album.getId());
    }
}
