package com.epam.audiospot.command;

public enum Forward {
    LOGIN("/WEB-INF/pages/landing.jsp"),
    MAIN("/WEB-INF/pages/main.jsp"),
    ADD_TRACK("/WEB-INF/pages/addtrack.jsp"),
    ADD_ALBUM("/WEB-INF/pages/addalbum.jsp"),
    VIEW_ALBUM("/WEB-INF/pages/viewalbum.jsp"),
    CLIENTS("/WEB-INF/pages/clients.jsp"),
    ALBUMS("/WEB-INF/pages/albums.jsp"),
    TRACKS("/WEB-INF/pages/tracks.jsp"),
    SHOW_COMMENTS("/WEB-INF/pages/comments.jsp"),
    PLAYLIST("/WEB-INF/pages/playlist.jsp"),
    PAY_ORDER("/WEB-INF/pages/payorder.jsp");

    private String path;

    Forward(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
