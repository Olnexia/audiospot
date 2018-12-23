package com.epam.audiospot.entity;

public class TrackAtPlaylist implements Entity {
    public static final String ID_LABEL = "track_at_playlist_id";
    public static final String PLAYLIST_ID_LABEL = "playlist_id";
    public static final String AUDIOTRACK_LABEL = "audiotrack_id";

    private Long id;
    private Long playlistId;
    private Long trackId;

    public TrackAtPlaylist(Long id, Long playlistId, Long trackId) {
        this.id = id;
        this.playlistId = playlistId;
        this.trackId = trackId;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getPlaylistId() {
        return playlistId;
    }

    public Long getTrackId() {
        return trackId;
    }
}
