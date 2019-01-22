package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class NameValidator extends AbstractStringValidator {
    private static final String NAME_PATTERN="[A-Za-z]{1,15}";
    private static final Pattern PATTERN = Pattern.compile(NAME_PATTERN);

    public NameValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
