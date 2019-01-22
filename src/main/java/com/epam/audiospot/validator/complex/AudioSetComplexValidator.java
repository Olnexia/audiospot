package com.epam.audiospot.validator.complex;

import com.epam.audiospot.validator.DescriptionValidator;
import com.epam.audiospot.validator.TitleValidator;
import com.epam.audiospot.validator.Validator;
import java.util.HashMap;
import java.util.Map;

public class AudioSetComplexValidator extends ComplexValidator<String> {
    private static final String TITLE_VALIDATE_MESSAGE = "titleDesc";
    private static final String DESCRIPTION_VALIDATE_MESSAGE = "descriptionDesc";
    private String title;
    private String description;

    public AudioSetComplexValidator(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    Map<String, Validator<String>> getValidationData() {
        HashMap<String,Validator<String>> validationData = new HashMap <>();
        validationData.put(title,new TitleValidator(TITLE_VALIDATE_MESSAGE));
        validationData.put(description,new DescriptionValidator(DESCRIPTION_VALIDATE_MESSAGE));
        return validationData;
    }
}
