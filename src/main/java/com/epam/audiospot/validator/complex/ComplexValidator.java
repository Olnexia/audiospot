package com.epam.audiospot.validator.complex;

import com.epam.audiospot.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class ComplexValidator<T> {
    public Optional <List <String>> validate() {
        List <String> messages = new ArrayList <>();

        for (Map.Entry <T, Validator <T>> entry : getValidationData().entrySet()) {
            Validator <T> validator = entry.getValue();
            T validationData = entry.getKey();
            Optional <String> optionalMessage = validator.validate(validationData);
            optionalMessage.ifPresent(messages::add);
        }

        return messages.isEmpty()
                ? Optional.empty()
                : Optional.of(messages);
    }

    abstract Map <T, Validator <T>> getValidationData();
}
