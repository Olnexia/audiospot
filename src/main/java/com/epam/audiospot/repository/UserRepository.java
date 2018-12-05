package com.epam.audiospot.repository;

import com.epam.audiospot.entity.User;
import java.util.List;
import java.util.Optional;

public class UserRepository implements Repository<User> {
    @Override
    public void add(User object) {

    }

    @Override
    public void remove(User object) {

    }

    @Override
    public void update(User object) {

    }

    @Override
    public List<User> query(Specification specification) {
        return null;
    }

    @Override
    public Optional<User> queryForSingleResult(Specification specification) {
        return Optional.empty();
    }
}
