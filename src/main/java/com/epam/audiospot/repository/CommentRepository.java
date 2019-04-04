package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.builder.CommentBuilder;
import com.epam.audiospot.connection.ConnectionWrapper;
import by.belstu.losik.audiospot.entity.Comment;

import java.util.LinkedHashMap;
import java.util.Map;

public class CommentRepository extends AbstractRepository <Comment> {
    private static final String TABLE_NAME = "comment";

    public CommentRepository(ConnectionWrapper connection) {
        super(connection);
    }

    @Override
    public Builder <Comment> getBuilder() {
        return new CommentBuilder();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map <String, Object> getFields(Comment comment) {
        Map <String, Object> fields = new LinkedHashMap <>();
        fields.put(Comment.TEXT_LABEL, comment.getText());
        fields.put(Comment.DATE_TIME_LABEL, comment.getDateTime());
        fields.put(Comment.USER_ID_LABEL, comment.getUser().getId());
        fields.put(Comment.TRACK_ID_LABEL, comment.getTrackId());
        return fields;
    }
}
