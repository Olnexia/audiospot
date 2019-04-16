package by.belstu.losik.service;

import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class AudioService {
    private GenericRepository<AudioTrack> repository;

    public AudioService(GenericRepository<AudioTrack> repository) {
        this.repository = repository;
    }
}
