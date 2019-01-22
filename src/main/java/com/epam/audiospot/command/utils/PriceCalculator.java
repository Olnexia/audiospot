package com.epam.audiospot.command.utils;

import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.service.AudioTrackService;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PriceCalculator {
    public BigDecimal calculateTotalPrice(Long orderId) throws ServiceException {
        AudioTrackService trackService = new AudioTrackService();
        List<AudioTrack> orderedTracks = trackService.findOrderedTracks(orderId);
        List<BigDecimal> prices = orderedTracks.stream().map(AudioTrack::getPrice).collect(Collectors.toList());
        return prices.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public BigDecimal calculateFinalPrice(Long orderId,BigDecimal userDiscount) throws ServiceException{
        BigDecimal totalPrice = calculateTotalPrice(orderId);
        return totalPrice.subtract(totalPrice.multiply(userDiscount.divide(new BigDecimal(100))));
    }
}
