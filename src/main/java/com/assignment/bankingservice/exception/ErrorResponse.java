package com.assignment.bankingservice.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an error response
 */
@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String message;
    private long timestamp;

    public ErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}
