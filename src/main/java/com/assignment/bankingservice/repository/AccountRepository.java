package com.assignment.bankingservice.repository;

import com.assignment.bankingservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Account entities.
 * Extends JpaRepository to provide basic CRUD operations on the Account entity.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByCustomer_Id(Long customerId);

}
