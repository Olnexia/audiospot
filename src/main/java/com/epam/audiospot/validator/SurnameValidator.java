package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class SurnameValidator extends AbstractStringValidator {
    private static final String SURNAME_PATTERN ="[A-Za-z]{1,32}";
    private static final Pattern PATTERN = Pattern.compile(SURNAME_PATTERN);

    public SurnameValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
