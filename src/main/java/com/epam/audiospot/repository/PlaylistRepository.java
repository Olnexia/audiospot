package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Playlist;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.repository.specification.Specification;

import java.util.List;
import java.util.Map;

public class PlaylistRepository extends AbstractRepository<Playlist> {
    private static final String TABLE_NAME = "user";

    public PlaylistRepository(ConnectionWrapper connection){
        super(connection);
    }

    @Override
    public Builder<Playlist> getBuilder() {
        return null;
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map<String, Object> getFields(Playlist object) {
        return null;
    }

    @Override
    public void add(Playlist object) {

    }

    @Override
    public void remove(Playlist object) {

    }

    @Override
    public void update(Playlist object) {

    }
}
