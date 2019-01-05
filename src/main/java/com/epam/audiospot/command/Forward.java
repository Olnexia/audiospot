package com.epam.audiospot.command;

public enum Forward {
    LOGIN("/WEB-INF/pages/login.jsp"),
    MAIN("/WEB-INF/pages/main.jsp"),
    ADD_TRACK("/WEB-INF/pages/addtrack.jsp"),
    CLIENTS("/WEB-INF/pages/clients.jsp"),
    TRACK_BUYING("/WEB-INF/pages/tracksbuying.jsp"),
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