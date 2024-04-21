package com.assignment.bankingservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * This centralized exception handling simplifies controllers by abstracting error handling and response formatting, allowing
 *  controllers to focus on handling business logic and request processing.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomServiceException.class)
    public ResponseEntity<ErrorResponse> handleCustomServiceException(CustomServiceException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameter(MissingServletRequestParameterException ex) {
        // Example of handling missing request parameters globally
        String missingParam = ex.getParameterName();
        return ResponseEntity.badRequest().body(missingParam + " parameter is missing");
    }
}
