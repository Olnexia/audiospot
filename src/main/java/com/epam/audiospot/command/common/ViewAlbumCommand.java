package com.epam.audiospot.command.common;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.command.Forward;
import com.epam.audiospot.entity.Album;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AlbumService;
import com.epam.audiospot.service.AudioTrackService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class ViewAlbumCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        Long albumId = Long.parseLong(request.getParameter("albumId"));
        AlbumService albumService = new AlbumService();
        AudioTrackService trackService = new AudioTrackService();
        try {
            Optional<Album> albumOptional = albumService.findAlbum(albumId);
            if(albumOptional.isPresent()){
                Album album = albumOptional.get();
                request.setAttribute("album",album);
            }
            List<AudioTrack> tracks = trackService.findTracksAtAlbum(albumId);
            request.setAttribute("tracks",tracks);
            return CommandResult.forward(Forward.VIEW_ALBUM.getPath());
        }catch (ServiceException e){
            throw new CommandExecutionException(e.getMessage(),e);
        }
    }
}
