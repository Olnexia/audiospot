package com.epam.audiospot.repository;

import com.epam.audiospot.entity.Entity;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.repository.specification.Specification;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entity> {
    void save(T object) throws RepositoryException;
    void add(T object) throws RepositoryException;
    void update (T object) throws RepositoryException;
    void remove (T object) throws RepositoryException;
    List<T> query(Specification specification) throws RepositoryException;
    List<T> queryForAll() throws RepositoryException;
    Optional<T> queryForSingleResult(Specification specification)throws RepositoryException;
}