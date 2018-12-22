package com.epam.audiospot.entity;

public class Artist implements Entity {
    public static final String ID_LABEL = "artist_id";
    public static final String NAME_LABEL = "name";
    public static final String COUNTRY_LABEL = "country";

    private Long id;
    private String name;
    private String country;

    public Artist(Long id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

