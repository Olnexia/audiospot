package com.epam.audiospot.entity;

public class Artist implements Entity {
    private final Long id;
    private String name;
    private String country;

    public Artist(long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

}

