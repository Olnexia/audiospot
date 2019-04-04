package com.epam.audiospot.service;

import by.belstu.losik.audiospot.entity.Role;
import by.belstu.losik.audiospot.entity.User;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.*;
import com.epam.audiospot.repository.factory.RepositoryFactory;
import com.epam.audiospot.repository.factory.UserRepositoryFactory;
import com.epam.audiospot.repository.specification.UserByIdSpecification;
import com.epam.audiospot.repository.specification.UserByLoginAndPasswordSpecification;
import com.epam.audiospot.repository.specification.UserByLoginSpecification;
import com.epam.audiospot.repository.specification.UserByRoleSpecification;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.Optional;

public class UserService {

    public Optional <User> login(String login, String password) throws ServiceException {
        String md5Password = DigestUtils.md5Hex(password).toUpperCase();
        UserByLoginAndPasswordSpecification specification = new UserByLoginAndPasswordSpecification(login, md5Password);
        try (RepositoryFactory <User> factory = new UserRepositoryFactory()) {
            Repository <User> repository = factory.createRepository();
            return repository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List <User> findClients() throws ServiceException {
        UserByRoleSpecification userByRoleSpecification = new UserByRoleSpecification(Role.CLIENT);
        try (RepositoryFactory <User> factory = new UserRepositoryFactory()) {
            Repository <User> repository = factory.createRepository();
            return repository.query(userByRoleSpecification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public User findUser(Long userId) throws ServiceException {
        UserByIdSpecification specification = new UserByIdSpecification(userId);
        try (RepositoryFactory <User> factory = new UserRepositoryFactory()) {
            Repository <User> repository = factory.createRepository();
            Optional <User> userOptional = repository.queryForSingleResult(specification);
            if (userOptional.isPresent()) {
                return userOptional.get();
            } else {
                throw new ServiceException("User is missing");
            }
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void saveUser(User user) throws ServiceException {
        try (RepositoryFactory <User> factory = new UserRepositoryFactory()) {
            Repository <User> repository = factory.createRepository();
            repository.save(user);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public boolean isLoginAvailable(String login) throws ServiceException {
        UserByLoginSpecification specification = new UserByLoginSpecification(login);
        try (RepositoryFactory <User> factory = new UserRepositoryFactory()) {
            Repository <User> repository = factory.createRepository();
            Optional <User> userOptional = repository.queryForSingleResult(specification);
            return !userOptional.isPresent();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
