package com.epam.audiospot.entity;

import java.math.BigDecimal;

public class AudioTrack implements Entity {
    private static final long serialVersionUID = 1656277176990370384L;

    public static final String ID_LABEL = "audiotrack_id";
    public static final String ALBUM_ID_LABEL = "album_id";
    public static final String AUTHOR_ID_LABEL = "author_id";
    public static final String TITLE_LABEL = "title";
    public static final String PRICE_LABEL = "price";
    public static final String GENRE_LABEL = "genre";

    private Long id;
    private Long albumId;
    private Artist artist;
    private String title;
    private Genre genre;
    private BigDecimal price;

    public AudioTrack(Long id, Long albumId, Artist artist, String title,
                      BigDecimal price, Genre genre) {
        this.id = id;
        this.albumId = albumId;
        this.artist = artist;
        this.title = title;
        this.price = price;
        this.genre = genre;
    }

    public static AudioTrack single(Artist artist, String title, BigDecimal price, Genre genre){
        return new AudioTrack(null,null,artist,title,price,genre);
    }

    public static AudioTrack albumTrack(Artist artist, String title, BigDecimal price, Genre genre,Long albumId){
        return new AudioTrack(null,albumId,artist,title,price,genre);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        AudioTrack track = (AudioTrack)object;
        return  id.equals(track.getId())
                && albumId.equals(track.getAlbumId())
                && artist.equals(track.getArtist())
                && title.equals(track.getTitle())
                && genre.equals(track.getGenre())
                && price.equals(track.getPrice());
    }

    @Override
    public int hashCode() {
        int result =17;
        final int prime = 31;
        result = prime * result + id.hashCode();
        result = prime * result + title.hashCode();
        result = prime * result + albumId.hashCode();
        result = prime * result + price.hashCode();
        result = prime * result + artist.hashCode();
        result = prime * result + genre.hashCode();
        return result;
    }

    @Override
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

    public Genre getGenre() {
        return genre;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
