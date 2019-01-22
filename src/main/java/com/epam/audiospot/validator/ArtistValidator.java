package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class ArtistValidator extends AbstractStringValidator {
    private static final String ARTIST_PATTERN="[A-Za-z0-9',. ]{1,32}";
    private static final Pattern PATTERN = Pattern.compile(ARTIST_PATTERN);

    public ArtistValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}