package org.practice.simplehomeorderservice.exception;

public class NotEnoughCreditException extends RuntimeException {
    public NotEnoughCreditException(String message) {
        super(message);
    }
}
