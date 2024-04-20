package com.assignment.bankingservice.exception;

/**
 * Custom exception class for handling service layer exceptions.
 */
public class CustomServiceException extends RuntimeException {
    public CustomServiceException(String message) {
        super(message);
    }
}