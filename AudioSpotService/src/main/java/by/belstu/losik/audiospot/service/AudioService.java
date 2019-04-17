package by.belstu.losik.audiospot.service;

import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AudioService {
    private static final String COUNT_TRACKS = "SELECT COUNT(*) FROM audiotrack";
    private Repository<AudioTrack> repository;

    public AudioService(Repository<AudioTrack> repository) {
        this.repository = repository;
    }

    public List<AudioTrack> findTracksPage(int page, int size) {
        return repository.findPage(page, size);
    }

    public int getTracksCount() {
        return repository.getSingleValue(COUNT_TRACKS, Collections.emptyMap(), Integer.class);
    }
}
