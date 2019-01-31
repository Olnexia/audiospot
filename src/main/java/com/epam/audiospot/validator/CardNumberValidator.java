package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class CardNumberValidator extends AbstractStringValidator {
    private static final String CARD_PATTERN = "[0-9]{13,16}";
    private static final Pattern PATTERN = Pattern.compile(CARD_PATTERN);

    public CardNumberValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
