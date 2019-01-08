package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Comment;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.IllegalBuildStateException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.UserService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

public class CommentBuilder implements Builder<Comment> {

    @Override
    public Comment build(ResultSet resultSet) throws SQLException, ServiceException {
        Long commentId = resultSet.getLong(Comment.ID_LABEL);
        Long trackId = resultSet.getLong(Comment.TRACK_ID_LABEL);
        Long userId = resultSet.getLong(Comment.USER_ID_LABEL);

        UserService service= new UserService();
        String text = resultSet.getString(Comment.TEXT_LABEL);
        Timestamp timestamp = resultSet.getTimestamp(Comment.DATE_TIME_LABEL);
        LocalDateTime dateTime = timestamp.toLocalDateTime();
        Optional<User> user = service.findUser(userId);
        if(user.isPresent()){
            return new Comment(commentId,user.get(),trackId,text,dateTime);
        }else{
            throw new IllegalBuildStateException("Missing user");
        }
    }
}
