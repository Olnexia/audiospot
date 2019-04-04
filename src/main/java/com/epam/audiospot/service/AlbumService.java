package com.epam.audiospot.service;

import by.belstu.losik.audiospot.entity.Album;
import by.belstu.losik.audiospot.entity.Artist;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.Repository;
import com.epam.audiospot.repository.factory.AlbumRepositoryFactory;
import com.epam.audiospot.repository.factory.RepositoryFactory;
import com.epam.audiospot.repository.specification.AlbumByIdSpecification;

import java.util.List;
import java.util.Optional;

public class AlbumService {

    public void addAlbum(Album album) throws ServiceException {
        try (RepositoryFactory <Album> factory = new AlbumRepositoryFactory()) {
            Repository <Album> repository = factory.createRepository();
            repository.add(album);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Album buildAlbum(String title, String artistName, int releaseYear) throws ServiceException {
        ArtistService artistService = new ArtistService();
        Optional <Artist> artistOptional = artistService.getArtist(artistName, true);
        if (!artistOptional.isPresent()) {
            throw new ServiceException("Artist is missing");
        }
        return new Album(null, title, artistOptional.get(), releaseYear);
    }

    public Optional <Album> findAlbum(Long id) throws ServiceException {
        AlbumByIdSpecification specification = new AlbumByIdSpecification(id);
        try (RepositoryFactory <Album> factory = new AlbumRepositoryFactory()) {
            Repository <Album> repository = factory.createRepository();
            return repository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List <Album> findAllAlbums() throws ServiceException {
        try (RepositoryFactory <Album> factory = new AlbumRepositoryFactory()) {
            Repository <Album> repository = factory.createRepository();
            return repository.queryForAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
