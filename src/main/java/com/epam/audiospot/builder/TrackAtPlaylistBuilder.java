//package com.epam.audiospot.builder;
//
//import com.epam.audiospot.entity.TrackAtPlaylist;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class TrackAtPlaylistBuilder implements Builder<TrackAtPlaylist> {
//
//    @Override
//    public TrackAtPlaylist build(ResultSet resultSet) throws SQLException {
//        Long id = resultSet.getLong(TrackAtPlaylist.ID_LABEL);
//        Long playlistId = resultSet.getLong(TrackAtPlaylist.PLAYLIST_ID_LABEL);
//        Long trackId = resultSet.getLong(TrackAtPlaylist.AUDIOTRACK_LABEL);
//        return new TrackAtPlaylist(id,playlistId,trackId);
//    }
//}
