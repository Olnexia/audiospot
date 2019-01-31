package com.epam.audiospot.validator;

import java.util.Optional;

public interface Validator<T> {
    Optional <String> validate(T object);
}
