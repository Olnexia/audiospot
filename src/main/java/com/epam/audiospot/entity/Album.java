package com.epam.audiospot.entity;

public class Album implements Entity {
    public static final String ID_LABEL = "album_id";
    public static final String TITLE_LABEL = "title";
    public static final String AUTHOR_ID_LABEL = "author_id";
    public static final String RELEASE_YEAR_LABEL = "release_year";

    private  Long id;
    private  String title;
    private  Artist artist;
    private int releaseYear;

    public Album(Long id, String title, Artist artist, int releaseYear) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
