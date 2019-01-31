package com.epam.audiospot.entity;

public class Artist implements Entity {
    private static final long serialVersionUID = -3557593784249973031L;

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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Artist artist = (Artist) object;
        return id.equals(artist.getId())
                && name.equals(artist.getName())
                && country.equals(artist.getCountry());
    }

    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + id.hashCode();
        result = prime * result + name.hashCode();
        result = prime * result + country.hashCode();
        return result;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}

