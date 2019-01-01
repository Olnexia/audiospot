package com.epam.audiospot.service;

import com.epam.audiospot.entity.Artist;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.ArtistRepository;
import com.epam.audiospot.repository.RepositoryCreator;
import com.epam.audiospot.repository.specification.ArtistByIdSpecification;
import com.epam.audiospot.repository.specification.ArtistByNameSpecification;

import java.util.Optional;

public class ArtistService implements Service{

    public Optional<Artist> getArtist(String name) throws ServiceException {
        ArtistByNameSpecification specification = new ArtistByNameSpecification(name);
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ArtistRepository repository = repositoryCreator.getArtistRepository();
            return repository.queryForSingleResult(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Optional<Artist> getArtist(Long id) throws ServiceException {
        ArtistByIdSpecification specification = new ArtistByIdSpecification(id);
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ArtistRepository repository = repositoryCreator.getArtistRepository();
            return repository.queryForSingleResult(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void addArtist(Artist artist) throws ServiceException{
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            ArtistRepository repository = repositoryCreator.getArtistRepository();
            repository.add(artist);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
