package com.epam.audiospot.repository;

import com.epam.audiospot.entity.Entity;
import java.util.List;
import java.util.Optional;

public interface Repository<T extends Entity> {
    void add(T object);
    void remove (T object);
    void update (T object);
    List<T> query(Specification specification);
    Optional<T> queryForSingleResult(Specification specification);
}