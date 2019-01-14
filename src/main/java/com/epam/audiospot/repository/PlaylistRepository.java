package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.builder.PlaylistBuilder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Playlist;

import java.util.HashMap;
import java.util.Map;

public class PlaylistRepository extends AbstractRepository<Playlist> {
    private static final String TABLE_NAME = "playlist";

    public PlaylistRepository(ConnectionWrapper connection){
        super(connection);
    }

    @Override
    public Builder<Playlist> getBuilder() {
        return new PlaylistBuilder();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map<String, Object> getFields(Playlist playlist) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(Playlist.ID_LABEL,playlist.getId());
        fields.put(Playlist.TITLE_LABEL,playlist.getTitle());
        fields.put(Playlist.DESCRIPTION_LABEL,playlist.getDescription());
        return fields;
    }
}
