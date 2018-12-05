package com.epam.audiospot.entity;

import java.math.BigDecimal;
import java.time.Year;

public class AudioTrack implements Entity {
    private final Long id;
    private final String title;
    private final BigDecimal price;
    private final Long artistId;
    private final Year releaseYear;
    private final Long albumId;

    public AudioTrack(long id, String title, BigDecimal price, long artistId,Year releaseYear, Long albumId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
        this.albumId = albumId;
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

    public Year getReleaseYear() {
        return releaseYear;
    }
}
