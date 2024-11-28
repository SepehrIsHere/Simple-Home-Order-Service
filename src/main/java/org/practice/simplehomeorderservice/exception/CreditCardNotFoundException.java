package org.practice.simplehomeorderservice.exception;

public class CreditCardNotFoundException extends RuntimeException {
    public CreditCardNotFoundException(String message) {
        super(message);
    }
}
