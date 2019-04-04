package com.epam.audiospot.builder;

import by.belstu.losik.audiospot.entity.AudioSet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AudioSetBuilder implements Builder <AudioSet> {

    @Override
    public AudioSet build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(AudioSet.ID_LABEL);
        String title = resultSet.getString(AudioSet.TITLE_LABEL);
        String description = resultSet.getString(AudioSet.DESCRIPTION_LABEL);
        return new AudioSet(id, title, description);
    }
}
