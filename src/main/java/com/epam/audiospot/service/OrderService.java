package com.epam.audiospot.service;

import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.OrderedTrack;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.OrderRepository;
import com.epam.audiospot.repository.OrderedTrackRepository;
import com.epam.audiospot.repository.RepositoryCreator;
import com.epam.audiospot.repository.specification.OrderByUserIdAndStatusSpecification;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Optional<Order> orderOptional = findOrder(userId,false);
        Order order;
        if(orderOptional.isPresent()){
            order = orderOptional.get();
        }else{
            order = new Order (null,userId,LocalDate.now(),false);
            saveOrder(order);
        }
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            OrderedTrackRepository repository = repositoryCreator.getOrderedTrackRepository();
            OrderedTrack orderedTrack = new OrderedTrack(null,order.getId(),trackId);
            repository.add(orderedTrack);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void saveOrder(Order order)throws ServiceException{
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            OrderRepository repository = repositoryCreator.getOrderRepository();
            repository.save(order);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void deleteOrder(Order order)throws ServiceException{
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            OrderRepository repository = repositoryCreator.getOrderRepository();
            repository.remove(order);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    //TODO make test
    public BigDecimal calculateTotalPrice(Long orderId) throws ServiceException{
        AudioTrackService trackService = new AudioTrackService();
        List<AudioTrack> orderedTracks = trackService.findOrderedTracks(orderId);
        List<BigDecimal> prices = orderedTracks.stream().map(AudioTrack::getPrice).collect(Collectors.toList());
        return prices.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
    }
}

