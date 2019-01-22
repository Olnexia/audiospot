package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class PriceValidator extends AbstractStringValidator {
    private static final String PRICE_PATTERN="^[0-9]+(\\.[0-9]{2})?$";
    private static final Pattern PATTERN = Pattern.compile(PRICE_PATTERN);

    public PriceValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
