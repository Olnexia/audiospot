package com.epam.audiospot.entity;

import java.math.BigDecimal;

public class AudioTrack implements Entity {
    private final Long id;
    private final Long albumId;
    private final Long artistId;
    private final String title;
    private final int releaseYear;
    private final Genre genre;
    private BigDecimal price;

    public AudioTrack(Long id, Long albumId, Long artistId, String title,
                      BigDecimal price, int releaseYear, Genre genre) {
        this.id = id;
        this.albumId = albumId;
        this.artistId = artistId;
        this.title = title;
        this.price = price;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public long getArtistId() {
        return artistId;
    }

    public long getAlbumId() {
        return albumId;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public Genre getGenre() {
        return genre;
    }
}
