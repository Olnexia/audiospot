package com.epam.audiospot.service;

import com.epam.audiospot.entity.*;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.AudioRepository;
import com.epam.audiospot.repository.RepositoryCreator;
import com.epam.audiospot.repository.UserRepository;
import com.epam.audiospot.repository.specification.UserByRoleSpecification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AudioTrackService implements Service {

    public void submitTrack(AudioTrack track) throws ServiceException{
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AudioRepository repository = repositoryCreator.getAudioRepository();
            repository.add(track);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<AudioTrack> findAllTracks() throws ServiceException{
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AudioRepository repository = repositoryCreator.getAudioRepository();
            return repository.queryForAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public AudioTrack buildTrack(String title, BigDecimal price, int releaseYear,
                                 Genre genre,String artistName) throws  ServiceException{
        ArtistService artistService = new ArtistService();
        Optional<Artist> artistOptional = artistService.getArtist(artistName);
        Artist artist;
        if(artistOptional.isPresent()) {
            artist = artistOptional.get();
        }else{
            artist = new Artist(null,artistName,null);
            artistService.addArtist(artist);
        }
        return AudioTrack.single(artist,title,price,releaseYear,genre);
    }
}
