package com.epam.audiospot.validator.complex;

import com.epam.audiospot.validator.*;
import java.util.*;

public class RegistrationComplexValidator extends ComplexValidator<String> {
    private static final String LOGIN_VALIDATE_MESSAGE = "loginDesc";
    private static final String NAME_VALIDATE_MESSAGE = "nameDesc";
    private static final String SURNAME_VALIDATE_MESSAGE = "surnameDesc";
    private static final String PASSWORD_VALIDATE_MESSAGE = "passwordDesc";
    private String login;
    private String name;
    private String surname;
    private String password;

    public RegistrationComplexValidator(String login, String name, String surname, String password) {
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    @Override
    Map<String, Validator<String>> getValidationData() {
        HashMap<String,Validator<String>> validationData = new HashMap <>();
        validationData.put(login,new LoginValidator(LOGIN_VALIDATE_MESSAGE));
        validationData.put(name,new NameValidator(NAME_VALIDATE_MESSAGE));
        validationData.put(surname,new SurnameValidator(SURNAME_VALIDATE_MESSAGE));
        validationData.put(password,new PasswordValidator(PASSWORD_VALIDATE_MESSAGE));
        return validationData;
    }
}
