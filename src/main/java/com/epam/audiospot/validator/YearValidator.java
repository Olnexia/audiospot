package com.epam.audiospot.validator;

import java.time.Year;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YearValidator implements Validator <String> {
    private static final String YEAR_PATTERN = "[0-9]{4}";
    private static final Pattern PATTERN = Pattern.compile(YEAR_PATTERN);
    private String validateMessage;

    public YearValidator(String validateMessage) {
        this.validateMessage = validateMessage;
    }

    @Override
    public Optional <String> validate(String validatingString) {
        Matcher matcher = PATTERN.matcher(validatingString);

        if (!matcher.matches()) {
            return Optional.of(validateMessage);
        }

        Year year = Year.parse(validatingString);
        Year currentYear = Year.now();

        return year.isAfter(currentYear)
                ? Optional.of(validateMessage)
                : Optional.empty();
    }
}
