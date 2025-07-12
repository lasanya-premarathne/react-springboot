package com.example.repository;

import com.example.models.entities.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository interface for managing TicketEntity objects.
 * Provides CRUD operations and custom queries for ticket data.
 */
@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    /**
     * Retrieves all tickets that have been purchased (have a customer ID).
     * @return List of tickets that have been purchased
     */
    List<TicketEntity> findAllByCustomerIdIsNotNull();

    /**
     * Finds tickets by vendor ID.
     * @param vendorId The ID of the vendor
     * @return List of tickets associated with the vendor
     */
    List<TicketEntity> findByVendorId(int vendorId);

    /**
     * Finds tickets purchased by a specific customer.
     * @param customerId The ID of the customer
     * @return List of tickets purchased by the customer
     */
    List<TicketEntity> findByCustomerId(int customerId);
}