package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Album;
import com.epam.audiospot.entity.Artist;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.ArtistService;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlbumBuilder implements Builder<Album> {

    @Override
    public Album build(ResultSet resultSet) throws SQLException, ServiceException {
        Long id = resultSet.getLong(Album.ID_LABEL);
        String title = resultSet.getString(Album.TITLE_LABEL);
        Long authorId = resultSet.getLong(Album.AUTHOR_ID_LABEL);
        int releaseYear = resultSet.getInt(Album.RELEASE_YEAR_LABEL);

        ArtistService artistService = new ArtistService();
        Artist artist = artistService.getArtist(authorId);

        return new Album(id,title,artist,releaseYear);
    }
}
