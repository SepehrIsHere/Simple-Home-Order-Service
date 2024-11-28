package org.practice.simplehomeorderservice.exception;

public class NotEnoughCredit extends RuntimeException {
    public NotEnoughCredit(String message) {
        super(message);
    }
}
