package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Playlist;
import com.epam.audiospot.exception.RepositoryException;

import java.util.List;

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
    public void add(Playlist object) {

    }

    @Override
    public void remove(Playlist object) {

    }

    @Override
    public void update(Playlist object) {

    }

    @Override
    public List<Playlist> query(Specification specification) throws RepositoryException {
        return null;
    }
}
