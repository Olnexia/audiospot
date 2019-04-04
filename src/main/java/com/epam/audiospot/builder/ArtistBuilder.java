package com.epam.audiospot.builder;

import by.belstu.losik.audiospot.entity.Artist;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArtistBuilder implements Builder <Artist> {

    @Override
    public Artist build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Artist.ID_LABEL);
        String name = resultSet.getString(Artist.NAME_LABEL);
        String country = (resultSet.getString(Artist.COUNTRY_LABEL) == null)
                ? "unknown"
                : resultSet.getString(Artist.COUNTRY_LABEL);
        return new Artist(id, name, country);
    }
}
