package com.epam.audiospot.repository.factory;

import by.belstu.losik.audiospot.entity.Artist;
import com.epam.audiospot.repository.ArtistRepository;
import com.epam.audiospot.repository.Repository;

public class ArtistRepositoryFactory extends RepositoryFactory <Artist> {
    @Override
    public Repository <Artist> createRepository() {
        return new ArtistRepository(getConnection());
    }
}
