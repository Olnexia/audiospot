package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Playlist;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaylistBuilder implements Builder<Playlist> {

    @Override
    public Playlist build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Playlist.ID_LABEL);
        String title = resultSet.getString(Playlist.TITLE_LABEL);
        String description = resultSet.getString(Playlist.DESCRIPTION_LABEL);
        return new Playlist(id,title,description);
    }
}
