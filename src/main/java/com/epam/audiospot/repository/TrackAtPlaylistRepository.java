//package com.epam.audiospot.repository;
//
//import com.epam.audiospot.builder.Builder;
//import com.epam.audiospot.builder.TrackAtPlaylistBuilder;
//import com.epam.audiospot.connection.ConnectionWrapper;
//import com.epam.audiospot.entity.TrackAtPlaylist;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class TrackAtPlaylistRepository extends AbstractRepository<TrackAtPlaylist> {
//    private static final String TABLE_NAME = "track_at_playlist";
//
//    public TrackAtPlaylistRepository(ConnectionWrapper connection){
//        super(connection);
//    }
//
//    @Override
//    public Builder getBuilder() {
//        return new TrackAtPlaylistBuilder();
//    }
//
//    @Override
//    public String getTableName() {
//        return TABLE_NAME;
//    }
//
//    @Override
//    public Map<String, Object> getFields(TrackAtPlaylist trackAtPlaylist) {
//        Map<String,Object> fields = new HashMap<>();
//        fields.put(TrackAtPlaylist.ID_LABEL,trackAtPlaylist.getId());
//        fields.put(TrackAtPlaylist.PLAYLIST_ID_LABEL,trackAtPlaylist.getPlaylistId());
//        fields.put(TrackAtPlaylist.AUDIOTRACK_LABEL,trackAtPlaylist.getTrackId());
//        return fields;
//    }
//}
