package com.epam.audiospot.repository.factory;

import com.epam.audiospot.entity.Album;
import com.epam.audiospot.repository.AlbumRepository;
import com.epam.audiospot.repository.Repository;

public class AlbumRepositoryFactory extends RepositoryFactory<Album> {
    @Override
    public Repository<Album> createRepository() {
        return new AlbumRepository(getConnection());
    }
}
