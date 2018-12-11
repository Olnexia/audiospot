package com.epam.audiospot.service;

import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.LoginException;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.repository.UserByLoginAndPasswordSpecification;
import com.epam.audiospot.repository.UserRepository;
import java.util.Optional;

public class UserService implements Service {
    public Optional<User> login(String login , String password) throws LoginException {
        UserByLoginAndPasswordSpecification specification = new UserByLoginAndPasswordSpecification(login,password);
        UserRepository repository = new UserRepository();
        try {
            return repository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new LoginException(e.getMessage(),e);
        }
    }
}
