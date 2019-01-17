package com.epam.audiospot.command;

public enum Redirect {
    HOME("/controller?command=home"),
    LOGIN("/controller?command=login"),
    ADD_TRACK("/controller?command=addTrack"),
    SHOW_CLIENTS("/controller?command=showClients"),
    VIEW_ALBUM("/controller?command=viewAlbum"),
    SHOW_TRACKS("/controller?command=showTracks"),
    SHOW_PLAYLIST("/controller?command=showPlaylist"),
    VIEW_AUDIOSET("/controller?command=viewAudioSet"),
    ADD_TO_SET("/controller?command=addTracks");

    private String path;

    Redirect(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
