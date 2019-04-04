package com.epam.audiospot.repository.factory;

import by.belstu.losik.audiospot.entity.Order;
import com.epam.audiospot.repository.OrderRepository;
import com.epam.audiospot.repository.Repository;

public class OrderRepositoryFactory extends RepositoryFactory <Order> {
    @Override
    public Repository <Order> createRepository() {
        return new OrderRepository(getConnection());
    }
}
