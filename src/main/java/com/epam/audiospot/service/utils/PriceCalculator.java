package com.epam.audiospot.service.utils;

import com.epam.audiospot.entity.AudioTrack;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class PriceCalculator {

    public BigDecimal calculateTotalPrice(List<AudioTrack> orderedTracks ) {
        List<BigDecimal> prices = orderedTracks.stream().map(AudioTrack::getPrice).collect(Collectors.toList());
        return prices.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public BigDecimal calculateFinalPrice(List<AudioTrack> orderedTracks,BigDecimal userDiscount){
        BigDecimal totalPrice = calculateTotalPrice(orderedTracks);
        return totalPrice.subtract(totalPrice.multiply(userDiscount.divide(new BigDecimal(100))).setScale( 2, RoundingMode.HALF_UP));
    }
}
