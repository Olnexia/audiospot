package com.epam.audiospot.command.factory;

import com.epam.audiospot.command.*;
import com.epam.audiospot.command.admin.*;
import com.epam.audiospot.command.client.*;
import com.epam.audiospot.command.common.*;

public enum CommandType {
    LOGIN("login", new LoginCommand()),
    HOME("home", new HomeCommand()),
    ADD_TRACK("addTrack",new AddTrackCommand()),
    SUBMIT_TRACK("submitTrack",new SubmitTrackCommand()),
    ADD_ALBUM("addAlbum",new AddAlbumCommand()),
    SUBMIT_ALBUM("submitAlbum",new SubmitAlbumCommand()),
    SHOW_ALBUMS("showAlbums",new ShowAlbumsCommand()),
    VIEW_ALBUM("viewAlbum",new ViewAlbumCommand()),
    ORDER_ALBUM("orderAlbum",new OrderAlbumCommand()),
    SHOW_CLIENTS("showClients",new ShowClientsCommand()),
    SHOW_COMMENTS("showComments",new ShowCommentsCommand()),
    CHANGE_CLIENT_STATUS("changeClientStatus",new ChangeClientStatusCommand()),
    SHOW_PLAYLIST("showPlaylist",new ShowPlaylistCommand()),
    SHOW_COMMENT("submitComment",new SubmitCommentCommand()),
    SHOW_TRACKS("showTracks",new ShowTracksCommand()),
    ORDER_TRACK("orderTrack",new OrderTrackCommand()),
    DELETE_ORDER("cancelOrder",new CancelOrderCommand()),
    PAY_ORDER("payOrder",new PayForOrderCommand()),
    SUBMIT_PAYMENT("submitPayment",new SubmitPaymentCommand()),
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
