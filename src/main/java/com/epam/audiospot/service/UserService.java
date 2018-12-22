package com.epam.audiospot.service;

import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.repository.*;
import com.epam.audiospot.repository.specification.UserByLoginAndPasswordSpecification;
import com.epam.audiospot.repository.specification.UserByRoleSpecification;

import java.util.List;
import java.util.Optional;

public class UserService implements Service {

    public Optional<User> login(String login , String password) throws CommandExecutionException {
        UserByLoginAndPasswordSpecification specification = new UserByLoginAndPasswordSpecification(login,password);
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository repository = repositoryCreator.getUserRepository();
            return repository.queryForSingleResult(specification);
        }catch (RepositoryException e){ //Service exception
            throw new CommandExecutionException(e.getMessage(),e);
        }
    }

    public List<User> findClients() throws CommandExecutionException{
        UserByRoleSpecification userByRoleSpecification = new UserByRoleSpecification(Role.CLIENT);
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository repository = repositoryCreator.getUserRepository();
            return repository.query(userByRoleSpecification);
        } catch (RepositoryException e) {
            throw new CommandExecutionException(e.getMessage(),e);
        }
    }
}
