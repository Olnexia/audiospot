package com.epam.audiospot.entity;

import java.time.LocalDateTime;

public class Comment implements Entity{
    private final Long id;
    private final Long userId;
    private final Long trackId;
    private final String text;
    private final LocalDateTime dateTime;

    public Comment(long id, long userId, long trackId, String text, LocalDateTime dateTime) {
        this.id = id;
        this.userId = userId;
        this.trackId = trackId;
        this.text = text;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getUserId() {
        return userId;
    }

    public long getTrackId() {
        return trackId;
    }
}
