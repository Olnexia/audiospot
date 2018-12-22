package com.epam.audiospot.command;

import com.epam.audiospot.command.admin.*;
import com.epam.audiospot.command.client.BuyTracksCommand;
import com.epam.audiospot.command.client.ShowPlaylistCommand;

public enum CommandType {
    LOGIN("login", new LoginCommand()),
    ADD_TRACK("addTrack",new AddTrackCommand()),
    SUBMIT_TRACK("submitTrack",new SubmitTrackCommand()),
    ADD_ALBUM("addAlbum",new AddAlbumCommand()),
    SHOW_PLAYLISTS("showPlaylists",new ShowPlaylistsCommand()),
    SHOW_CLIENTS("showClients",new ShowClientsCommand()),
    SHOW_PLAYLIST("showPlaylist",new ShowPlaylistCommand()),
    BUY_TRACKS("buyTracks",new BuyTracksCommand()),
    LOGOUT("logout",new LogoutCommand());

    private Command command;
    private String commandText;

    CommandType(String commandText, Command command) {
        this.commandText = commandText;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getText() {
        return commandText;
    }

    public static CommandType getCurrentCommand(String command) {
        for (CommandType commandType : CommandType.values()) {
            String commandText = commandType.getText();
            if (commandText.equals(command)) {
                return commandType;
            }
        }
        throw new IllegalArgumentException();
    }
}
