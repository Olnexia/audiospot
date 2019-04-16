package com.epam.javalab.travelagency.validation.validator;

import com.epam.javalab.travelagency.validation.annotation.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private static final String PASS_PATTERN = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
    private static final Pattern PATTERN = Pattern.compile(PASS_PATTERN);

    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        Matcher matcher = PATTERN.matcher(password);
        return matcher.matches();
    }
}
