package com.epam.audiospot.entity;

import java.math.BigDecimal;
import java.time.Year;

public class AudioTrack implements Entity {
    private final long id;
    private final String title;
    private final BigDecimal price;
    private final long artistId;
    private final Year releaseYear;
    private final Long albumId;

    private AudioTrack(long id, String title, BigDecimal price, long artistId,Year releaseYear, Long albumId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.artistId = artistId;
        this.releaseYear = releaseYear;
        this.albumId = albumId;
    }

    public static AudioTrack Single(long id, String title, BigDecimal price, long artistId,int releaseYear) {
        return new AudioTrack(id,title,price,artistId,Year.of(releaseYear),null);
    }

    public static AudioTrack AlbumAludiTrack(long id,String title,BigDecimal price,long artistId,int releaseYear,long albumId){
        return new AudioTrack(id,title,price,artistId,Year.of(releaseYear),albumId);
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
