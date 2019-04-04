package com.epam.audiospot.builder;

import by.belstu.losik.audiospot.entity.Comment;
import by.belstu.losik.audiospot.entity.User;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CommentBuilder implements Builder <Comment> {

    @Override
    public Comment build(ResultSet resultSet) throws SQLException, ServiceException {
        Long commentId = resultSet.getLong(Comment.ID_LABEL);
        Long trackId = resultSet.getLong(Comment.TRACK_ID_LABEL);
        Long userId = resultSet.getLong(Comment.USER_ID_LABEL);

        String text = resultSet.getString(Comment.TEXT_LABEL);
        Timestamp timestamp = resultSet.getTimestamp(Comment.DATE_TIME_LABEL);
        LocalDateTime dateTime = timestamp.toLocalDateTime();

        UserService service = new UserService();
        User user = service.findUser(userId);

        return new Comment(commentId, user, trackId, text, dateTime);
    }
}
