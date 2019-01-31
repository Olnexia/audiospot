package com.epam.audiospot.validator.complex;

import com.epam.audiospot.validator.*;

import java.util.*;

public class TrackComplexValidator extends ComplexValidator <String> {
    private static final String ARTIST_VALIDATE_MESSAGE = "artistTitleDesc";
    private static final String TITLE_VALIDATE_MESSAGE = "artistTitleDesc";
    private static final String PRICE_VALIDATE_MESSAGE = "priceDesc";
    private String artist;
    private String title;
    private String price;

    public TrackComplexValidator(String artist, String title, String price) {
        this.artist = artist;
        this.title = title;
        this.price = price;
    }

    @Override
    Map <String, Validator <String>> getValidationData() {
        HashMap <String, Validator <String>> validationData = new HashMap <>();
        validationData.put(artist, new ArtistValidator(ARTIST_VALIDATE_MESSAGE));
        validationData.put(title, new TitleValidator(TITLE_VALIDATE_MESSAGE));
        validationData.put(price, new PriceValidator(PRICE_VALIDATE_MESSAGE));
        return validationData;
    }
}