package com.assignment.bankingservice.service;

import com.assignment.bankingservice.dto.CustomerInfomationDTO;

import java.math.BigDecimal;

public interface BankingService {

       String createAccount(Long customerId, BigDecimal initialCredit);

       CustomerInfomationDTO getCustomerInfo(Long customerId);
}
