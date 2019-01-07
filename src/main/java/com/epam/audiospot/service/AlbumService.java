package com.epam.audiospot.service;

import com.epam.audiospot.entity.Album;
import com.epam.audiospot.entity.Artist;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.AlbumRepository;
import com.epam.audiospot.repository.creator.RepositoryCreator;
import com.epam.audiospot.repository.specification.AlbumByIdSpecification;

import java.util.List;
import java.util.Optional;

public class AlbumService {

    public void addAlbum(Album album) throws ServiceException {
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AlbumRepository repository = repositoryCreator.getAlbumRepository();
            repository.add(album);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Album buildAlbum(String title,String artistName, int releaseYear) throws  ServiceException{
        ArtistService artistService = new ArtistService();
        Optional<Artist> artistOptional = artistService.getArtist(artistName,true);
        if(!artistOptional.isPresent()){
            throw new ServiceException("Artist is missing");
        }
        return new Album(null,title,artistOptional.get(),releaseYear);
    }

    public Optional<Album> findAlbum(Long id) throws ServiceException {
        AlbumByIdSpecification specification = new AlbumByIdSpecification(id);
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AlbumRepository repository = repositoryCreator.getAlbumRepository();
            return repository.queryForSingleResult(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Album> findAllAlbums() throws ServiceException{
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AlbumRepository repository = repositoryCreator.getAlbumRepository();
            return repository.queryForAll();
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
