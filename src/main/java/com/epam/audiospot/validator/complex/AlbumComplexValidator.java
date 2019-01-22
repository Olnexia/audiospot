package com.epam.audiospot.validator.complex;

import com.epam.audiospot.validator.ArtistValidator;
import com.epam.audiospot.validator.TitleValidator;
import com.epam.audiospot.validator.Validator;
import com.epam.audiospot.validator.YearValidator;
import java.util.HashMap;
import java.util.Map;

public class AlbumComplexValidator extends ComplexValidator<String> {
    private static final String ARTIST_VALIDATE_MESSAGE = "artistTitleDesc";
    private static final String TITLE_VALIDATE_MESSAGE = "artistTitleDesc";
    private static final String YEAR_VALIDATE_MESSAGE = "yearDesc";
    private String artist;
    private String title;
    private String year;

    public AlbumComplexValidator(String artist, String title, String year) {
        this.artist = artist;
        this.title = title;
        this.year = year;
    }

    @Override
    Map<String, Validator<String>> getValidationData() {
        HashMap<String,Validator<String>> validationData = new HashMap <>();
        validationData.put(artist,new ArtistValidator(ARTIST_VALIDATE_MESSAGE));
        validationData.put(title,new TitleValidator(TITLE_VALIDATE_MESSAGE));
        validationData.put(year,new YearValidator(YEAR_VALIDATE_MESSAGE));
        return validationData;
    }
}
