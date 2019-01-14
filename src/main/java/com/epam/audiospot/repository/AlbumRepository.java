package com.epam.audiospot.repository;

import com.epam.audiospot.builder.AlbumBuilder;
import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Album;

import java.util.HashMap;
import java.util.Map;

public class AlbumRepository extends AbstractRepository<Album> {
    private static final String TABLE_NAME = "album";

    public AlbumRepository(ConnectionWrapper connection){
        super(connection);
    }

    @Override
    public Builder<Album> getBuilder() {
        return new AlbumBuilder();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map<String, Object> getFields(Album album) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(Album.ID_LABEL,album.getId());
        fields.put(Album.AUTHOR_ID_LABEL,album.getArtist().getId());
        fields.put(Album.TITLE_LABEL,album.getTitle());
        fields.put(Album.RELEASE_YEAR_LABEL,album.getReleaseYear());
        return fields;
    }
}
