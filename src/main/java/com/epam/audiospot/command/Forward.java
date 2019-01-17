package com.epam.audiospot.command;

public enum Forward {
    LOGIN("/WEB-INF/pages/landing.jsp"),
    MAIN("/WEB-INF/pages/main.jsp"),
    ADD_TRACK("/WEB-INF/pages/addTrack.jsp"),
    ADD_ALBUM("/WEB-INF/pages/addAlbum.jsp"),
    VIEW_ALBUM("/WEB-INF/pages/viewAlbum.jsp"),
    CLIENTS("/WEB-INF/pages/clients.jsp"),
    ALBUMS("/WEB-INF/pages/albums.jsp"),
    TRACKS("/WEB-INF/pages/tracks.jsp"),
    SHOW_COMMENTS("/WEB-INF/pages/comments.jsp"),
    PLAYLIST("/WEB-INF/pages/playlist.jsp"),
    PAY_ORDER("/WEB-INF/pages/payOrder.jsp"),
    ERROR_PAGE("\"/WEB-INF/pages/errors/defaultErrorPage.jsp");

    private String path;

    Forward(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
