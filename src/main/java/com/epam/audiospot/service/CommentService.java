package com.epam.audiospot.service;

import com.epam.audiospot.entity.Comment;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.CommentRepository;
import com.epam.audiospot.repository.creator.RepositoryCreator;

public class CommentService{

    public void save(Comment comment)throws ServiceException {
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            CommentRepository repository = repositoryCreator.getCommentRepository();
            repository.save(comment);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
