package com.example.services;

import com.example.models.entities.CustomerEntity;
import com.example.repository.CustomerRepository;
import com.example.services.util.RandomFourDigitGenerationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Service class handling business logic for customer operations.
 * Manages customer creation, retrieval, and related operations.
 */
@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final RandomFourDigitGenerationService uniqueIdService;

    public CustomerService(RandomFourDigitGenerationService uniqueIdService,
                           CustomerRepository customerRepository) {
        this.uniqueIdService = uniqueIdService;
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieves all customers from the database.
     */
    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Retrieves a customer by their ID.
     * @throws RuntimeException if customer is not found
     */
    public CustomerEntity getCustomerById(int id) {
        return customerRepository.findByCustomerId(id);
    }

    /**
     * Adds a new customer or retrieves existing one if name already exists.
     * @param customerEntity The customer to be added
     * @return The saved or existing customer
     */
    public CustomerEntity addCustomer(CustomerEntity customerEntity) {
        // Check if customer already exists by name
        if (!customerRepository.existsByCustomerName(customerEntity.getCustomerName())) {
            // Generate a unique ID for new customer
            customerEntity.setCustomerId(uniqueIdService.generateUniqueRandomFourDigit());
            return customerRepository.save(customerEntity);
        }
        // Return existing customer if name already exists
        return customerRepository.findByCustomerName(customerEntity.getCustomerName());
    }
}