package com.epam.audiospot.command;

import com.epam.audiospot.command.admin.AddAlbumCommand;
import com.epam.audiospot.command.admin.AddTrackCommand;
import com.epam.audiospot.command.admin.ShowClientsCommand;
import com.epam.audiospot.command.admin.ShowPlaylistsCommand;
import com.epam.audiospot.command.client.BuyTracksCommand;
import com.epam.audiospot.command.client.ShowPlaylistCommand;

public class CommandFactory {

    public static Command create(String command){
        switch (command){
            case "login":
                return new LoginCommand();
            case "showPlaylist":
                return new ShowPlaylistCommand();
            case "buyTracks":
                return new BuyTracksCommand();
            case "showClients":
                return new ShowClientsCommand();
            case "showPlaylists":
                return new ShowPlaylistsCommand();
            case "addTrack":
                return new AddTrackCommand();
            case "addAlbum":
                return new AddAlbumCommand();
            default: throw new IllegalArgumentException("Unknown command " + command);

        }
    }
}
