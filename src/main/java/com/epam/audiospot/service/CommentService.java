package com.epam.audiospot.service;

import com.epam.audiospot.entity.Comment;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.Repository;
import com.epam.audiospot.repository.factory.CommentRepositoryFactory;
import com.epam.audiospot.repository.factory.RepositoryFactory;
import com.epam.audiospot.repository.specification.CommentsByTrackIdSpecification;
import java.util.List;

public class CommentService{

    public void saveComment(Comment comment)throws ServiceException {
        try(RepositoryFactory<Comment> factory = new CommentRepositoryFactory()){
            Repository<Comment> repository = factory.createRepository();
            repository.save(comment);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Comment> findComments(Long trackId) throws ServiceException{
        CommentsByTrackIdSpecification specification = new CommentsByTrackIdSpecification(trackId);
        try(RepositoryFactory<Comment> factory = new CommentRepositoryFactory()){
            Repository<Comment> repository = factory.createRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
