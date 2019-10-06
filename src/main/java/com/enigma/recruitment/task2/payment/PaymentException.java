package com.enigma.recruitment.task2.payment;

public class PaymentException extends RuntimeException {
    public PaymentException(String errorMessage) {
        super(errorMessage);
    }
}