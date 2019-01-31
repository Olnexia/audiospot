package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.Album;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AlbumService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAlbumsCommand implements Command {
    private static final String ALBUMS_ATTR = "albums";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        AlbumService service = new AlbumService();
        List <Album> albums = service.findAllAlbums();

        request.setAttribute(ALBUMS_ATTR, albums);
        return CommandResult.forward(Forward.ALBUMS.getPath());
    }
}
