package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.builder.UserBuilder;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.RepositoryException;
import java.util.List;

public class UserRepository extends AbstractRepository<User> {

    @Override
    public List<User> query(Specification specification) throws RepositoryException{
        String preparedQuery = specification.toSql();
        List<Object> parameters = specification.getParameters();
        try {
            return executeQuery(preparedQuery,parameters);
        } catch (RepositoryException e) {
            throw new RepositoryException(e.getMessage(),e);
        }
    }

    @Override
    public Builder<User> getBuilder() {
        return new UserBuilder();
    }

    @Override
    public void add(User object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(User object) {
        throw new UnsupportedOperationException();

    }

    @Override
    public void update(User object) {
        throw new UnsupportedOperationException();

    }
}
