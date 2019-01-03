package com.epam.audiospot.command;

public enum Redirect {
    HOME("/controller?command=home"),
    ADD_TRACK("/controller?command=addTrack"),
    SHOW_CLIENTS("/controller?command=showClients"),
    BUY_TRACKS("/controller?command=buyTracks"),
    SHOW_PLAYLIST("/controller?command=showPlaylist");

    private String path;

    Redirect(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
