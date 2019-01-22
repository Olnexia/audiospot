package com.epam.audiospot.validator;

import java.util.regex.Pattern;

public class PasswordValidator extends AbstractStringValidator {
    private static final String PASSWORD_PATTERN="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    private static final Pattern PATTERN = Pattern.compile(PASSWORD_PATTERN);

    public PasswordValidator(String validateMessage) {
        super(validateMessage);
    }

    @Override
    Pattern getPattern() {
        return PATTERN;
    }
}
