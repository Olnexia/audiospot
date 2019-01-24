package com.epam.audiospot.service;

import com.epam.audiospot.service.utils.PriceCalculator;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.entity.OrderedTrack;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.Repository;
import com.epam.audiospot.repository.factory.AudioRepositoryFactory;
import com.epam.audiospot.repository.factory.OrderRepositoryFactory;
import com.epam.audiospot.repository.factory.OrderedTrackRepositoryFactory;
import com.epam.audiospot.repository.factory.RepositoryFactory;
import com.epam.audiospot.repository.specification.AudioTracksByAlbumIdSpecification;
import com.epam.audiospot.repository.specification.AudioTracksByAudioSetIdSpecification;
import com.epam.audiospot.repository.specification.OrderByUserIdAndStatusSpecification;
import com.epam.audiospot.repository.specification.Specification;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class OrderService {

    public Order findOrder(Long userId,boolean paid) throws ServiceException {
        Optional<Order> orderOptional = findOptionalOrder(userId,paid);
        if(orderOptional.isPresent()){
            return orderOptional.get();
        }else{
            throw new ServiceException("Order is missing");
        }
    }

    public void orderTrack(Long userId,Long trackId)throws ServiceException{
        Optional<Order> orderOptional = findOptionalOrder(userId,false);
        Order order;
        if(orderOptional.isPresent()){
            order = orderOptional.get();
        }else{
            order = new Order (null,userId,LocalDate.now(),false);
            saveOrder(order);
        }
        try(RepositoryFactory<OrderedTrack> factory = new OrderedTrackRepositoryFactory()){
            Repository<OrderedTrack> repository = factory.createRepository();
            OrderedTrack orderedTrack = new OrderedTrack(null,order.getId(),trackId);
            repository.add(orderedTrack);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void orderAlbum(Long userId,Long albumId) throws ServiceException{
        AudioTracksByAlbumIdSpecification specification = new AudioTracksByAlbumIdSpecification(albumId);
        orderAvailableTracks(userId,specification);
    }

    public void orderAudioSet(Long userId,Long audioSetId) throws ServiceException {
        AudioTracksByAudioSetIdSpecification specification = new AudioTracksByAudioSetIdSpecification(audioSetId);
        orderAvailableTracks(userId, specification);
    }

    private void orderAvailableTracks(Long userId, Specification specification) throws  ServiceException{
        AudioTrackService trackService = new AudioTrackService();
        List<AudioTrack> availableTracks = trackService.findAvailableTracks(userId);
        try(RepositoryFactory<AudioTrack> factory = new AudioRepositoryFactory()){
            Repository<AudioTrack> repository = factory.createRepository();
            List<AudioTrack> tracks = repository.query(specification);
            for(AudioTrack track : tracks){
                if(availableTracks.contains(track)){
                    orderTrack(userId,track.getId());
                }
            }
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void saveOrder(Order order)throws ServiceException{
        try(RepositoryFactory<Order> factory = new OrderRepositoryFactory()){
            Repository<Order> repository = factory.createRepository();
            repository.save(order);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void deleteOrder(Order order)throws ServiceException{
        try(RepositoryFactory<Order> factory = new OrderRepositoryFactory()){
            Repository<Order> repository = factory.createRepository();
            repository.remove(order);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public Optional<Order> findOptionalOrder(Long userId,boolean paid) throws ServiceException{
        OrderByUserIdAndStatusSpecification specification = new OrderByUserIdAndStatusSpecification(userId,paid);
        try(RepositoryFactory<Order> factory = new OrderRepositoryFactory()){
            Repository<Order> repository = factory.createRepository();
            return repository.queryForSingleResult(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public BigDecimal calculateTotalPrice(Long orderId) throws ServiceException {
        AudioTrackService trackService = new AudioTrackService();
        List<AudioTrack> orderedTracks = trackService.findOrderedTracks(orderId);
        PriceCalculator calculator = new PriceCalculator();
        return calculator.calculateTotalPrice(orderedTracks);
    }

    public BigDecimal calculateFinalPrice(Long orderId,BigDecimal userDiscount) throws ServiceException{
        AudioTrackService trackService = new AudioTrackService();
        List<AudioTrack> orderedTracks = trackService.findOrderedTracks(orderId);
        PriceCalculator calculator = new PriceCalculator();
        return calculator.calculateFinalPrice(orderedTracks,userDiscount);
    }
}

