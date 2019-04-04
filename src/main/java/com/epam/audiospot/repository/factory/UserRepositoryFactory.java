package com.epam.audiospot.repository.factory;

import by.belstu.losik.audiospot.entity.User;
import com.epam.audiospot.repository.Repository;
import com.epam.audiospot.repository.UserRepository;

public class UserRepositoryFactory extends RepositoryFactory <User> {
    @Override
    public Repository <User> createRepository() {
        return new UserRepository(getConnection());
    }
}
