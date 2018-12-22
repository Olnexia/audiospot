package com.epam.audiospot.entity;

public class Album implements Entity {
    private  Long id;
    private  String title;
    private  long artistId;

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

    public void setId(Long id) {
        this.id = id;
    }
}
