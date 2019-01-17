package com.epam.audiospot.entity;

public class TrackAtAudioSet implements Entity {
    private static final long serialVersionUID = -2572777226453273248L;

    public static final String ID_LABEL = "track_at_audioset_id";
    public static final String AUDIOSET_ID_LABEL = "audioset_id";
    public static final String AUDIOTRACK_LABEL = "audiotrack_id";

    private Long id;
    private Long audioSetId;
    private Long trackId;

    public TrackAtAudioSet(Long id, Long audioSetId, Long trackId) {
        this.id = id;
        this.audioSetId = audioSetId;
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        TrackAtAudioSet trackAtAudioSet = (TrackAtAudioSet) object;
        return  id.equals(trackAtAudioSet.getId())
                && audioSetId.equals(trackAtAudioSet.getAudioSetId())
                && trackId.equals(trackAtAudioSet.getTrackId());
    }

    @Override
    public int hashCode() {
        int result =17;
        final int prime = 31;
        result = prime * result + id.hashCode();
        result = prime * result + audioSetId.hashCode();
        result = prime * result + trackId.hashCode();
        return result;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getAudioSetId() {
        return audioSetId;
    }

    public Long getTrackId() {
        return trackId;
    }
}
