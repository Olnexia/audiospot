package com.epam.audiospot.entity;

import java.time.LocalDateTime;

public class Comment implements Entity{
    private static final long serialVersionUID = 223618021188002931L;

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

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Comment comment = (Comment) object;
        return  id.equals(comment.getId())
                && user.equals(comment.getUser())
                && trackId.equals(comment.getTrackId())
                && text.equals(comment.getText())
                && dateTime.equals(comment.getDateTime());
    }

    @Override
    public int hashCode() {
        int result =17;
        final int prime = 31;
        result = prime * result + id.hashCode();
        result = prime * result + user.hashCode();
        result = prime * result + trackId.hashCode();
        result = prime * result + text.hashCode();
        result = prime * result + dateTime.hashCode();
        return result;
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
