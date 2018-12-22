package com.epam.audiospot.entity;

public class Album implements Entity {
    private  Long id;
    private  String title;
    private  Long artistId;

    public Album(long id, String title, long artistId) {
        this.id = id;
        this.title = title;
        this.artistId = artistId;
    }

    public Long getId() {
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
