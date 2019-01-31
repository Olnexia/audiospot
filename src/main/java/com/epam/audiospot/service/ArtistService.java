package com.epam.audiospot.service;

import com.epam.audiospot.entity.Artist;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.Repository;
import com.epam.audiospot.repository.factory.ArtistRepositoryFactory;
import com.epam.audiospot.repository.factory.RepositoryFactory;
import com.epam.audiospot.repository.specification.ArtistByIdSpecification;
import com.epam.audiospot.repository.specification.ArtistByNameSpecification;

import java.util.Optional;

public class ArtistService {

    public Optional <Artist> getArtist(String name, boolean create) throws ServiceException {
        ArtistByNameSpecification specification = new ArtistByNameSpecification(name);
        try (RepositoryFactory <Artist> factory = new ArtistRepositoryFactory()) {
            Repository <Artist> repository = factory.createRepository();
            Optional <Artist> artist = repository.queryForSingleResult(specification);
            if (!artist.isPresent() && create) {
                Artist newArtist = new Artist(null, name, null);
                addArtist(newArtist);
                artist = Optional.of(newArtist);
            }
            return artist;
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Artist getArtist(Long id) throws ServiceException {
        ArtistByIdSpecification specification = new ArtistByIdSpecification(id);
        try (RepositoryFactory <Artist> factory = new ArtistRepositoryFactory()) {
            Repository <Artist> repository = factory.createRepository();
            Optional <Artist> optionalArtist = repository.queryForSingleResult(specification);
            if (optionalArtist.isPresent()) {
                return optionalArtist.get();
            } else {
                throw new ServiceException("Artist is missing");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void addArtist(Artist artist) throws ServiceException {
        try (RepositoryFactory <Artist> factory = new ArtistRepositoryFactory()) {
            Repository <Artist> repository = factory.createRepository();
            repository.add(artist);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
