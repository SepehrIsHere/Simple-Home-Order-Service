package org.practice.simplehomeorderservice.exception;

public class OrderStatusException extends RuntimeException {
    public OrderStatusException(String message) {
        super(message);
    }
}
