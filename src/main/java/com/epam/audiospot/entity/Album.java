package com.epam.audiospot.entity;

public class Album implements Entity {
    private static final long serialVersionUID = -8014961169717699941L;

    public static final String ID_LABEL = "album_id";
    public static final String TITLE_LABEL = "title";
    public static final String AUTHOR_ID_LABEL = "author_id";
    public static final String RELEASE_YEAR_LABEL = "release_year";

    private Long id;
    private String title;
    private Artist artist;
    private int releaseYear;

    public Album(Long id, String title, Artist artist, int releaseYear) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.releaseYear = releaseYear;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Album album = (Album) object;
        return id.equals(album.getId())
                && title.equals(album.getTitle())
                && artist.equals(album.getArtist())
                && releaseYear == album.getReleaseYear();
    }

    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + id.hashCode();
        result = prime * result + title.hashCode();
        result = prime * result + artist.hashCode();
        result = prime * result + Integer.hashCode(releaseYear);
        return result;
    }

    @Override
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

    @Override
    public void setId(Long id) {
        this.id = id;
    }


}
