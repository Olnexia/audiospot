package com.epam.audiospot.repository;

import com.epam.audiospot.builder.AudioSetBuilder;
import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.connection.ConnectionWrapper;
import by.belstu.losik.audiospot.entity.AudioSet;

import java.util.LinkedHashMap;
import java.util.Map;

public class AudioSetRepository extends AbstractRepository <AudioSet> {
    private static final String TABLE_NAME = "audioset";

    public AudioSetRepository(ConnectionWrapper connection) {
        super(connection);
    }

    @Override
    public Builder <AudioSet> getBuilder() {
        return new AudioSetBuilder();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map <String, Object> getFields(AudioSet audioSet) {
        Map <String, Object> fields = new LinkedHashMap <>();
        fields.put(AudioSet.ID_LABEL, audioSet.getId());
        fields.put(AudioSet.TITLE_LABEL, audioSet.getTitle());
        fields.put(AudioSet.DESCRIPTION_LABEL, audioSet.getDescription());
        return fields;
    }
}
