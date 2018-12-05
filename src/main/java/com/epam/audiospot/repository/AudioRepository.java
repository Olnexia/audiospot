package com.epam.audiospot.repository;

import com.epam.audiospot.builder.AudioBuilder;
import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.entity.AudioTrack;

public class AudioRepository extends AbstractRepository<AudioTrack> {



    @Override
    public Builder getBuilder() {
        return new AudioBuilder();
    }
}
