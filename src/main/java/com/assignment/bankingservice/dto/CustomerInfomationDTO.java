package com.assignment.bankingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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
