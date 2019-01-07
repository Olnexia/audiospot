package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.command.Redirect;
import com.epam.audiospot.entity.Album;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AlbumService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SubmitAlbumCommand implements Command {
    private static final String ARTIST_LABEL = "artist";
    private static final Logger logger = LogManager.getLogger(SubmitTrackCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String title = request.getParameter(Album.TITLE_LABEL);
        int releaseYear = Integer.parseInt(request.getParameter(Album.RELEASE_YEAR_LABEL));
        String artistName = request.getParameter(ARTIST_LABEL);

        AlbumService service = new AlbumService();
        CommandResult commandResult;
        try{
            Album album = service.buildAlbum(title,artistName,releaseYear);
            service.addAlbum(album);
            logger.info("New album with id "+album.getId()+" added.");
            HttpSession session = request.getSession(false);
            session.setAttribute("albumId",album.getId());
            commandResult = CommandResult.redirect(Redirect.VIEW_ALBUM.getPath()+"&albumId="+album.getId());
        }catch (ServiceException e){
            logger.error("An error occurred while adding album: ",e);
            commandResult = CommandResult.forward(Forward.ADD_ALBUM.getPath()); //also set some text message?
        }

        //TODO here need some message for success

        return commandResult;
    }
}
