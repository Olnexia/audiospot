package com.epam.audiospot.entity;

public class Album implements Entity {
    private final Long id;
    private final String title;
    private final long artistId;

    public Album(long id, String title, long artistId) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public long getArtistId() {
        return artistId;
    }
}
