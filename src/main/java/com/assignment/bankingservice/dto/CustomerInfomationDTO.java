package com.assignment.bankingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Data Transfer Object (DTO) for customer information.
 * Attributes:
 * - name: The first name of the customer.
 * - surname: The last name of the customer.
 * - balance: The current balance of the customer's account
 * - transactions: A list of {@link TransactionDTO} objects representing transactions associated with the customer's account.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerInfomationDTO {
    private String name;
    private String surname;
    private BigDecimal balance;
    private List<TransactionDTO> transactions;
}
