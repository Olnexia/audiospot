package com.epam.audiospot.builder;

import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Genre;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AudioBuilder implements Builder<AudioTrack> {
    private static final String ID_LABEL = "audiotrack_id";
    private static final String ALBUM_ID_LABEL = "album_id";
    private static final String AUTHOR_ID_LABEL = "author_id";
    private static final String TITLE_LABEL = "title";
    private static final String PRICE_LABEL = "price";
    private static final String RELEASE_YEAR_LABEL = "release_year";
    private static final String GENRE_LABEL = "genre";

    @Override
    public AudioTrack build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID_LABEL);
        String title = resultSet.getString(TITLE_LABEL);
        BigDecimal price = resultSet.getBigDecimal(PRICE_LABEL);
        Long authorId = resultSet.getLong(AUTHOR_ID_LABEL);
        int releaseYear = resultSet.getInt(RELEASE_YEAR_LABEL);
        Long albumId = resultSet.getLong(ALBUM_ID_LABEL);
        String genreContent = resultSet.getString(GENRE_LABEL);
        Genre genre = Genre.valueOf(genreContent.toUpperCase());
        return new AudioTrack(id,albumId,authorId,title,price,releaseYear,genre);
    }
}
