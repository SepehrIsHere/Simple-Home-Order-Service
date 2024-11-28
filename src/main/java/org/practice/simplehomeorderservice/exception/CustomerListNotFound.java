package org.practice.simplehomeorderservice.exception;

public class CustomerListNotFound extends RuntimeException {
    public CustomerListNotFound(String message) {
        super(message);
    }
}
