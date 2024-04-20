package com.assignment.bankingservice.repository;

import com.assignment.bankingservice.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccount_Id(Long accountId);

    List<Transaction> findByAccount_Customer_Id(Long customerId);
}
