package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Comment;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentBuilder implements Builder<Comment> {

    @Override
    public Comment build(ResultSet resultSet) throws SQLException {
        Long commentId = resultSet.getLong(Comment.ID_LABEL);
        Long trackId = resultSet.getLong(Comment.TRACK_ID_LABEL);
        Long userId = resultSet.getLong(Comment.USER_ID_LABEL);
        String text = resultSet.getString(Comment.TEXT_LABEL);
        Timestamp timestamp = resultSet.getTimestamp(Comment.DATE_TIME_LABEL);
        LocalDateTime dateTime = timestamp.toLocalDateTime();

        return new Comment(commentId,userId,trackId,text,dateTime);
    }
}
