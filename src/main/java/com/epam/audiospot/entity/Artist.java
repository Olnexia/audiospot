package com.epam.audiospot.entity;

public class Artist implements Entity {
    private final long id;
    private final String name;
    private final String country;
    private final Genre genre;

    public Artist(long id, String name, String country, Genre genre) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Genre getGenre() {
        return genre;
    }
}

