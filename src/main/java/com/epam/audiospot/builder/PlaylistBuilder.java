package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Playlist;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistBuilder implements Builder<Playlist> {

    @Override
    public Playlist build(ResultSet resultSet) throws SQLException {
        return null;
    }
}
