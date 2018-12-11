package com.epam.audiospot.repository;

import com.epam.audiospot.entity.Entity;
import com.epam.audiospot.exception.RepositoryException;
import java.util.List;

public interface Repository<T extends Entity> {
    void add(T object);
    void remove (T object);
    void update (T object);
    List<T> query(Specification specification) throws RepositoryException;
}