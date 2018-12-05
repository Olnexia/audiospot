package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Album;
import com.epam.audiospot.entity.AudioTrack;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;

public class AudioBuilder implements Builder<AudioTrack> {
    private static final String ID_LABEL = "id";
    private static final String TITLE_LABEL = "title";
    private static final String PRICE_LABEL = "price";
    private static final String ARTIST_LABEL = "artist";
    private static final String RELEASE_YEAR_LABEL = "release_year";
    private static final String ALBUM_LABEL = "album";

    @Override
    public AudioTrack build(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong(ID_LABEL);
        String title = resultSet.getString(TITLE_LABEL);
        BigDecimal price = resultSet.getBigDecimal(PRICE_LABEL);
        Long artistId = resultSet.getLong(ARTIST_LABEL);
        int year = resultSet.getInt(RELEASE_YEAR_LABEL);
        Year releaseYear = Year.of(year);
        Long albumId = resultSet.getLong(ALBUM_LABEL);
        return new AudioTrack(id, title, price, artistId, releaseYear, albumId);

    }
}
