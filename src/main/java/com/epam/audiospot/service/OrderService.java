package com.epam.audiospot.service;

import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.OrderedTrack;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.OrderRepository;
import com.epam.audiospot.repository.OrderedTrackRepository;
import com.epam.audiospot.repository.RepositoryCreator;
import com.epam.audiospot.repository.specification.OrderByUserIdAndStatusSpecification;

import java.time.LocalDate;
import java.util.Optional;

public class OrderService implements Service {

    public Optional<Order> findOrder(Long userId,boolean paid) throws ServiceException {
        OrderByUserIdAndStatusSpecification specification = new OrderByUserIdAndStatusSpecification(userId,paid);
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            OrderRepository repository = repositoryCreator.getOrderRepository();
            return repository.queryForSingleResult(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void orderTrack(Long userId,Long trackId)throws ServiceException{
        Optional<Order> order = findOrder(userId,false);
        Long orderId;
        if(order.isPresent()){
            orderId = order.get().getId();
        }else{
            Order newOrder = new Order (null,userId,LocalDate.now(),false);
            addOrder(newOrder);
            orderId = newOrder.getId();
        }
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            OrderedTrackRepository repository = repositoryCreator.getOrderedTrackRepository();
            OrderedTrack orderedTrack = new OrderedTrack(null,orderId,trackId);
            repository.add(orderedTrack);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void addOrder(Order order)throws ServiceException{
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            OrderRepository repository = repositoryCreator.getOrderRepository();
            repository.add(order);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}

