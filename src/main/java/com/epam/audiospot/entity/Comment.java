package com.epam.audiospot.entity;

import java.time.LocalDate;

public class Comment implements Entity{
    private final long id;
    private final String text;
    private final LocalDate date;
    private final long userId;
    private final long trackId;

    public Comment(long id, String text, LocalDate date, long userId, long trackId) {
        this.id = id;
        this.text = text;
        this.date = date;
        this.userId = userId;
        this.trackId = trackId;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getUserId() {
        return userId;
    }

    public long getTrackId() {
        return trackId;
    }
}
