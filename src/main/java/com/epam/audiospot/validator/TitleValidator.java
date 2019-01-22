package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class TitleValidator extends AbstractStringValidator {
    private static final String TITLE_PATTERN="[A-Za-z0-9',. ]{1,32}";
    private static final Pattern PATTERN = Pattern.compile(TITLE_PATTERN);

    public TitleValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
