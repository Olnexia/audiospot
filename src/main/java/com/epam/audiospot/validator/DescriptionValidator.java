package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class DescriptionValidator extends AbstractStringValidator {
    private static final String DESCRIPTION_PATTERN=".{1,200}";
    private static final Pattern PATTERN = Pattern.compile(DESCRIPTION_PATTERN);

    public DescriptionValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
