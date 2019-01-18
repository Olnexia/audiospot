package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.Album;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AlbumService;
import com.epam.audiospot.service.AudioTrackService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ViewAlbumCommand implements Command {
    private static final String ALBUM_ID_PARAM = "albumId";
    private static final String ALBUM_ATTR = "album";
    private static final String TRACKS_ATTR = "tracks";

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long albumId = Long.parseLong(request.getParameter(ALBUM_ID_PARAM));

        AlbumService albumService = new AlbumService();
        Optional<Album> albumOptional = albumService.findAlbum(albumId);
        if(albumOptional.isPresent()) {
            Album album = albumOptional.get();
            request.setAttribute(ALBUM_ATTR, album);
        }

        AudioTrackService trackService = new AudioTrackService();
        List<AudioTrack> tracks = trackService.findTracksAtAlbum(albumId);

        request.setAttribute(TRACKS_ATTR,tracks);
        return CommandResult.forward(Forward.VIEW_ALBUM.getPath());
    }
}
