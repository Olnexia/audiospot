package com.epam.audiospot.service;

import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.repository.UserByLoginAndPasswordSpecification;
import com.epam.audiospot.repository.UserByRoleSpecification;
import com.epam.audiospot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public class UserService implements Service {

    public Optional<User> login(String login , String password) throws CommandExecutionException {
        UserByLoginAndPasswordSpecification specification = new UserByLoginAndPasswordSpecification(login,password);
        UserRepository repository = new UserRepository();
        try {
            return repository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new CommandExecutionException(e.getMessage(),e);
        }
    }

    public List<User> findClients() throws CommandExecutionException{
        UserByRoleSpecification userByRoleSpecification = new UserByRoleSpecification(Role.CLIENT);
        UserRepository userRepository = new UserRepository();
        try {
            return userRepository.query(userByRoleSpecification);
        } catch (RepositoryException e) {
            throw new CommandExecutionException(e.getMessage(),e);
        }
    }
}
