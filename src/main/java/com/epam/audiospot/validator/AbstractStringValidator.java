package com.epam.audiospot.validator;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractStringValidator implements Validator<String>{
    private String validateMessage;

    public AbstractStringValidator(String validateMessage){
        this.validateMessage = validateMessage;
    }

    @Override
    public Optional<String> validate(String validatingString) {
        Matcher matcher = getPattern().matcher(validatingString);
        return matcher.matches()
                ?Optional.empty()
                :Optional.of(validateMessage);
    }

    abstract Pattern getPattern();
}
