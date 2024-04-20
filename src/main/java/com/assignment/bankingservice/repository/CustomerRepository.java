package com.assignment.bankingservice.repository;

import com.assignment.bankingservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for Customer entities.
 * Extends JpaRepository to provide basic CRUD operations on the Customer entity.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
