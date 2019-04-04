package com.epam.audiospot.service.utils;

import by.belstu.losik.audiospot.entity.Artist;
import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.entity.Genre;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PriceCalculatorTest {
    private static final AudioTrack FIRST_TEST_AUDIO = new AudioTrack(42L, 42L, new Artist(42L, "", ""), "", new BigDecimal("0.99"), Genre.ROCK);
    private static final AudioTrack SECOND_TEST_AUDIO = new AudioTrack(42L, 42L, new Artist(42L, "", ""), "", new BigDecimal("0.78"), Genre.ROCK);
    private static final AudioTrack THIRD_TEST_AUDIO = new AudioTrack(42L, 42L, new Artist(42L, "", ""), "", new BigDecimal("0.85"), Genre.ROCK);
    private static final BigDecimal TEST_USER_DISCOUNT = new BigDecimal("2.5");
    private static final BigDecimal EXPECTED_TOTAL_PRICE = new BigDecimal("2.62");
    private static final BigDecimal EXPECTED_FINAL_PRICE = new BigDecimal("2.55");
    private PriceCalculator calculator = new PriceCalculator();

    @Test
    public void shouldCalculateTotalPrice() {
        //given
        List <AudioTrack> testTrackList = new ArrayList <>();
        testTrackList.add(FIRST_TEST_AUDIO);
        testTrackList.add(SECOND_TEST_AUDIO);
        testTrackList.add(THIRD_TEST_AUDIO);
        //when
        BigDecimal actualTotalPrice = calculator.calculateTotalPrice(testTrackList);
        //then
        Assert.assertEquals(EXPECTED_TOTAL_PRICE, actualTotalPrice);
    }

    @Test
    public void shouldCalculateFinalPrice() {
        //given
        List <AudioTrack> testTrackList = new ArrayList <>();
        testTrackList.add(FIRST_TEST_AUDIO);
        testTrackList.add(SECOND_TEST_AUDIO);
        testTrackList.add(THIRD_TEST_AUDIO);
        //when
        BigDecimal actualFinalPrice = calculator.calculateFinalPrice(testTrackList, TEST_USER_DISCOUNT);
        //then
        Assert.assertEquals(EXPECTED_FINAL_PRICE, actualFinalPrice);
    }
}