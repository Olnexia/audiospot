package com.epam.audiospot.repository.factory;

import by.belstu.losik.audiospot.entity.OrderedTrack;
import com.epam.audiospot.repository.OrderedTrackRepository;
import com.epam.audiospot.repository.Repository;

public class OrderedTrackRepositoryFactory extends RepositoryFactory <OrderedTrack> {
    @Override
    public Repository <OrderedTrack> createRepository() {
        return new OrderedTrackRepository(getConnection());
    }
}
