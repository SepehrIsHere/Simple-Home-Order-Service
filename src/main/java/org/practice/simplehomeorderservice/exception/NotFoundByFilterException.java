package org.practice.simplehomeorderservice.exception;

public class NotFoundByFilterException extends RuntimeException {
    public NotFoundByFilterException(String message) {
        super(message);
    }
}
