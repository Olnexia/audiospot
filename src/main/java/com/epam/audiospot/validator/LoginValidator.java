package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class LoginValidator extends AbstractStringValidator {
    private static final String LOGIN_PATTERN="[A-Za-z0-9_]{1,15}";
    private static final Pattern PATTERN = Pattern.compile(LOGIN_PATTERN);

    public LoginValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
