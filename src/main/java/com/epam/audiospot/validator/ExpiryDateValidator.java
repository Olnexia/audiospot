package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class ExpiryDateValidator extends AbstractStringValidator {
    private static final String EXPIRY_PATTERN = "(0[1-9]|1[012])/[0-9]{2}";
    private static final Pattern PATTERN = Pattern.compile(EXPIRY_PATTERN);

    public ExpiryDateValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
