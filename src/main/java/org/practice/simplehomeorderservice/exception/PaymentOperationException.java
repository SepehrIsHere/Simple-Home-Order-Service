package org.practice.simplehomeorderservice.exception;

public class PaymentOperationException extends RuntimeException {
    public PaymentOperationException(String message) {
        super(message);
    }
}
