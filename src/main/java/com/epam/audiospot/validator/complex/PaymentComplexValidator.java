package com.epam.audiospot.validator.complex;

import com.epam.audiospot.validator.CardNumberValidator;
import com.epam.audiospot.validator.CvcValidator;
import com.epam.audiospot.validator.ExpiryDateValidator;
import com.epam.audiospot.validator.Validator;
import java.util.HashMap;
import java.util.Map;

public class PaymentComplexValidator extends ComplexValidator<String> {
    private static final String CARD_VALIDATE_MESSAGE = "CcnDesc";
    private static final String CVC_VALIDATE_MESSAGE = "CvcDesc";
    private static final String EXPIRY_VALIDATE_MESSAGE = "ccExpDesc";
    private String cardNumber;
    private String cvc;
    private String expiryDate;

    public PaymentComplexValidator(String cardNumber, String cvc, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cvc = cvc;
        this.expiryDate = expiryDate;
    }

    @Override
    Map<String, Validator<String>> getValidationData() {
        HashMap<String,Validator<String>> validationData = new HashMap <>();
        validationData.put(cardNumber,new CardNumberValidator(CARD_VALIDATE_MESSAGE));
        validationData.put(cvc,new CvcValidator(CVC_VALIDATE_MESSAGE));
        validationData.put(expiryDate,new ExpiryDateValidator(EXPIRY_VALIDATE_MESSAGE));
        return validationData;
    }
}
