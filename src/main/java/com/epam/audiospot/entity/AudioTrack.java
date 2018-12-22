package com.epam.audiospot.entity;

import java.math.BigDecimal;

public class AudioTrack implements Entity {
    public static final String ID_LABEL = "audiotrack_id";
    public static final String ALBUM_ID_LABEL = "album_id";
    public static final String AUTHOR_ID_LABEL = "author_id";
    public static final String AUTHOR_LABEL = "author";
    public static final String TITLE_LABEL = "title";
    public static final String PRICE_LABEL = "price";
    public static final String RELEASE_YEAR_LABEL = "release_year";
    public static final String GENRE_LABEL = "genre";

    private Long id;
    private Long albumId;
    private Artist artist;
    private String title;
    private int releaseYear;
    private Genre genre;
    private BigDecimal price;

    public AudioTrack(Long id, Long albumId, Artist artist, String title,
                      BigDecimal price, int releaseYear, Genre genre) {
        this.id = id;
        this.albumId = albumId;
        this.artist = artist;
        this.title = title;
        this.price = price;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public static AudioTrack single(Artist artist, String title, BigDecimal price, int releaseYear, Genre genre){
        return new AudioTrack(null,null,artist,title,price,releaseYear,genre);
    }

    public AudioTrack( Long albumId, Artist artist, String title,
                      BigDecimal price, int releaseYear, Genre genre) {
        this.albumId = albumId;
        this.artist = artist;
        this.title = title;
        this.price = price;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Artist getArtist() {
        return artist;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
