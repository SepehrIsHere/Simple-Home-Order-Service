package org.practice.simplehomeorderservice.exception;

public class InvalidOrderStatus extends RuntimeException {
    public InvalidOrderStatus(String message) {
        super(message);
    }
}
