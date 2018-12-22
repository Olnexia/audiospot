package com.epam.audiospot.entity;

import java.time.LocalDateTime;

public class Comment implements Entity{
    private Long id;
    private Long userId;
    private Long trackId;
    private String text;
    private LocalDateTime dateTime;

    public Comment(long id, long userId, long trackId, String text, LocalDateTime dateTime) {
        this.id = id;
        this.userId = userId;
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

    public Long getUserId() {
        return userId;
    }

    public Long getTrackId() {
        return trackId;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
