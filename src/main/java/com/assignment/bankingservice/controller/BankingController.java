package com.assignment.bankingservice.controller;

import com.assignment.bankingservice.dto.CustomerInfomationDTO;
import com.assignment.bankingservice.exception.CustomServiceException;
import com.assignment.bankingservice.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * REST Controller for banking operations.
 * Endpoints:
 * - POST /api/accounts: Creates a new account for an existing customer with an initial credit amount.
 * - GET /api/accounts/customer-info: Retrieves detailed information about a customer, including their transactions, based on the customer ID.
 */
@RestController
@RequestMapping("/api")
public class BankingController {

    @Autowired
    private BankingService bankingService;

    /**
     * Creates an account for an existing customer based on the customer ID and an initial credit amount.
     *
     * @param customerId    the ID of the customer
     * @param initialCredit the initial amount to credit the new account
     * @return the newly created account details
     */
    @PostMapping("/accounts")
    public ResponseEntity<String> createAccount(@RequestParam Long customerId, @RequestParam BigDecimal initialCredit) {
        // Check if values are negative
        if (customerId < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer ID must not be negative.");
        }
        if (initialCredit.compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Initial credit must not be negative.");
        }
        try {
            String result = bankingService.createAccount(customerId, initialCredit);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (CustomServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Generic exception handling for unforeseen issues.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred.");
        }
    }

    /**
     * Get the information of customer based on its customer id.
     * @param customerId ID of the customer
     * @return CustomerInfomationDTO that has customer information.
     */
    @GetMapping("/accounts/customer-info")
    public ResponseEntity<?> getCustomerInfo(@RequestParam Long customerId) {
        // Check if values are negative
        if (customerId < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer ID must not be negative.");
        }
        try {
            CustomerInfomationDTO customerInfo = bankingService.getCustomerInfo(customerId);
            return ResponseEntity.ok(customerInfo);
        } catch (CustomServiceException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred.");
        }
    }
}


