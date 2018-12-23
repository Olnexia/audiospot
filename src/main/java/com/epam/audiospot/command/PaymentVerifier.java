package com.epam.audiospot.command;

import java.math.BigDecimal;

public interface PaymentVerifier {
    boolean verify(String cardNumber, String cvc, String expiry, BigDecimal sum);
}
