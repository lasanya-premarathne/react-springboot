package com.example.repository;

import com.example.models.entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing CustomerEntity objects.
 * Provides CRUD operations and custom queries for customer data.
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

    /**
     * Checks if a customer exists with the given name.
     * @param name The customer name to search for
     * @return true if a customer with the given name exists, false otherwise
     */
    boolean existsByCustomerName(String name);

    /**
     * Retrieves a customer by their unique identifier.
     * @param customerId The ID of the customer to find
     * @return The CustomerEntity if found, null otherwise
     */
    CustomerEntity findByCustomerId(int customerId);

    /**
     * Retrieves a customer by their name.
     * @param customerName The name of the customer to find
     * @return The CustomerEntity if found, null otherwise
     */
    CustomerEntity findByCustomerName(String customerName);
}