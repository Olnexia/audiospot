package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class CvcValidator extends AbstractStringValidator {
    private static final String CVC_PATTERN = "[0-9]{3}";
    private static final Pattern PATTERN = Pattern.compile(CVC_PATTERN);

    public CvcValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
