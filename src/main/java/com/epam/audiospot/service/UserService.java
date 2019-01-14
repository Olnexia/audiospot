package com.epam.audiospot.service;

import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.*;
import com.epam.audiospot.repository.creator.RepositoryCreator;
import com.epam.audiospot.repository.specification.UserByIdSpecification;
import com.epam.audiospot.repository.specification.UserByLoginAndPasswordSpecification;
import com.epam.audiospot.repository.specification.UserByLoginSpecification;
import com.epam.audiospot.repository.specification.UserByRoleSpecification;
import org.apache.commons.codec.digest.DigestUtils;
import java.util.List;
import java.util.Optional;

public class UserService{

    public Optional<User> login(String login , String password) throws ServiceException {
        String md5Password = DigestUtils.md5Hex(password).toUpperCase();
        UserByLoginAndPasswordSpecification specification = new UserByLoginAndPasswordSpecification(login,md5Password);
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository repository = repositoryCreator.getUserRepository();
            return repository.queryForSingleResult(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<User> findClients() throws ServiceException{
        UserByRoleSpecification userByRoleSpecification = new UserByRoleSpecification(Role.CLIENT);
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository repository = repositoryCreator.getUserRepository();
            return repository.query(userByRoleSpecification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Optional<User> findUser(Long userId)throws ServiceException{
        UserByIdSpecification specification = new UserByIdSpecification(userId);
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            UserRepository repository = repositoryCreator.getUserRepository();
            return repository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void saveUser(User user) throws ServiceException{
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            UserRepository repository = repositoryCreator.getUserRepository();
            repository.save(user);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public boolean isLoginAvailable(String login) throws ServiceException{
        UserByLoginSpecification specification = new UserByLoginSpecification(login);
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
         UserRepository repository = repositoryCreator.getUserRepository();
         Optional<User> userOptional = repository.queryForSingleResult(specification);
         return !userOptional.isPresent();
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
