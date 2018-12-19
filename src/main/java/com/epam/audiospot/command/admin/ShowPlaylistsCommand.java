package com.epam.audiospot.command.admin;

import com.epam.audiospot.command.Command;
import com.epam.audiospot.command.CommandResult;
import com.epam.audiospot.entity.Playlist;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.service.PlaylistService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowPlaylistsCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandExecutionException {
        PlaylistService playlistService = new PlaylistService();
//        List<Playlist> playlists = playlistService.findPlaylists();
//        request.setAttribute("playlists",playlists);
        return CommandResult.forward("/WEB-INF/pages/playlists.jsp");
    }
}
