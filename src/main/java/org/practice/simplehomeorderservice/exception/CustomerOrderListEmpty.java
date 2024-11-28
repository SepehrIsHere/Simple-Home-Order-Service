package org.practice.simplehomeorderservice.exception;

public class CustomerOrderListEmpty extends RuntimeException {
    public CustomerOrderListEmpty(String message) {
        super(message);
    }
}
