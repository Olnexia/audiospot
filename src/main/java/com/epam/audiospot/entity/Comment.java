package com.epam.audiospot.entity;

import java.time.LocalDateTime;

public class Comment implements Entity{
    public static final String ID_LABEL = "comment_id";
    public static final String USER_ID_LABEL = "user_id";
    public static final String TRACK_ID_LABEL = "audiotrack_id";
    public static final String TEXT_LABEL = "text";
    public static final String DATE_TIME_LABEL = "date_time";

    private Long id;
    private User user;
    private Long trackId;
    private String text;
    private LocalDateTime dateTime;

    public Comment(Long id, User user, Long trackId, String text, LocalDateTime dateTime) {
        this.id = id;
        this.user = user;
        this.trackId = trackId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public User getUser() {
        return user;
    }

    public Long getTrackId() {
        return trackId;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
