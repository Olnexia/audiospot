package com.epam.audiospot.repository.factory;

import by.belstu.losik.audiospot.entity.Comment;
import com.epam.audiospot.repository.CommentRepository;
import com.epam.audiospot.repository.Repository;

public class CommentRepositoryFactory extends RepositoryFactory <Comment> {
    @Override
    public Repository <Comment> createRepository() {
        return new CommentRepository(getConnection());
    }
}
