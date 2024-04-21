package com.assignment.bankingservice.service;

import com.assignment.bankingservice.dto.CustomerInfomationDTO;
import com.assignment.bankingservice.dto.TransactionDTO;
import com.assignment.bankingservice.exception.CustomServiceException;
import com.assignment.bankingservice.model.Account;
import com.assignment.bankingservice.model.Customer;
import com.assignment.bankingservice.model.Transaction;
import com.assignment.bankingservice.repository.AccountRepository;
import com.assignment.bankingservice.repository.CustomerRepository;
import com.assignment.bankingservice.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling banking operations such as creating accounts
 * and managing transactions.
 */
@Service
public class BankingService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    /**
     * Creates new account for a customer
     *
     * @param customerId    the ID of the customer who owns the account
     * @param initialCredit the initial amount to be credited to the account
     * @return the created account with or without an initial transaction
     */
    @Transactional
    public String createAccount(Long customerId, BigDecimal initialCredit) {
        try {
            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new CustomServiceException("Customer not found with ID: " + customerId));

            Account newAccount = new Account();
            newAccount.setCustomer(customer);
            newAccount.setBalance(initialCredit);
            accountRepository.save(newAccount);

            if (initialCredit.compareTo(BigDecimal.ZERO) > 0) {
                createTransaction(newAccount, initialCredit);
            }
            return "Account created successfully";
        } catch (DataAccessException e) {
            throw new CustomServiceException("Database error occurred while creating the account.");
        } catch (CustomServiceException e) {
            throw e;
        }
    }

    /**
     * Create transaction object and set the values.
     *
     * @param account
     * @param amount
     */
    private void createTransaction(Account account, BigDecimal amount) {
        Transaction transaction = new Transaction();
        Date now = new Date();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setTimestamp(now);
        transactionRepository.save(transaction);
    }

    /**
     * Retrieves  customer information based on customer id.
     *
     * @param customerId ID of customer
     * @return CustomerInfomationDTO
     */
    public CustomerInfomationDTO getCustomerInfo(Long customerId) {

        // Fetch transactions for the account
        List<Transaction> transactions = transactionRepository.findByAccount_Customer_Id(customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomServiceException("Customer not found with ID: " + customerId));

        if (transactions == null) {
            throw new CustomServiceException("Customer not found for account ID: " + customerId);
        }

        return convertToCustomerInformationDTO(transactions, customer);
    }

    /**
     * This method convert Entity to DTO
     *
     * @param transactions transaction list of particular customer.
     * @param customer     customer ID
     * @return CustomerInfomationDTO
     */
    private CustomerInfomationDTO convertToCustomerInformationDTO(List<Transaction> transactions, Customer customer) {

        // Calculate balance from transactions
        BigDecimal balance = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Balace " + balance);

        // Convert transactions to TransactionDTO list
        List<TransactionDTO> transactionDTOs = transactions.stream().map(transaction -> {
            TransactionDTO dto = new TransactionDTO();
            dto.setAmount(transaction.getAmount());
            dto.setTimestamp(transaction.getTimestamp().toString());
            return dto;
        }).collect(Collectors.toList());

        // Construct and return CustomerInfoDTO
        CustomerInfomationDTO customerInfoDTO = new CustomerInfomationDTO();
        customerInfoDTO.setName(customer.getName());
        customerInfoDTO.setSurname(customer.getSurname());
        customerInfoDTO.setBalance(balance);
        customerInfoDTO.setTransactions(transactionDTOs);

        return customerInfoDTO;

    }
}

