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
 *
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
        try {
            if (customerId == null || initialCredit == null) {
                return ResponseEntity.badRequest().body("Customer ID and Initial Credit must not be null.");
            }

            String result = bankingService.createAccount(customerId, initialCredit);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (CustomServiceException e) {
            // Assuming CustomServiceException is a runtime exception for cases like customer not found.
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


