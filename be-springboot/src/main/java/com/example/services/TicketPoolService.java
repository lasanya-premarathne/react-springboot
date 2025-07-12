package com.example.services;

import com.example.models.entities.SysConEntity;
import com.example.models.entities.TicketEntity;
import com.example.repository.CustomerRepository;
import com.example.repository.SysConRepository;
import com.example.repository.TicketRepository;
import com.example.repository.VendorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service responsible for managing the ticket pool system.
 * Handles ticket creation, distribution, and purchasing in a thread-safe manner.
 * Uses synchronized collections and methods to ensure thread safety in a multi-threaded environment.
 */
@Slf4j
@Service
@Transactional
public class TicketPoolService {
    // Thread-safe list to store available tickets
    private final List<TicketEntity> ticketPool;

    // Thread-safe list to store purchased tickets
    private final List<TicketEntity> purchasedTickets;

    // Counter for total tickets added to the system
    private Long totalTicketsAdded;

    // Repository dependencies
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final TicketRepository ticketRepository;
    private final SysConRepository sysConRepository;

    /**
     * Constructor initializing the service with required repositories and collections.
     */
    public TicketPoolService(
            TicketRepository ticketRepository,
            VendorRepository vendorRepository,
            CustomerRepository customerRepository,
            SysConRepository sysConRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.vendorRepository = vendorRepository;
        this.customerRepository = customerRepository;
        this.sysConRepository = sysConRepository;

        // Initialize thread-safe collections
        this.ticketPool = Collections.synchronizedList(new ArrayList<>());
        this.purchasedTickets = Collections.synchronizedList(new ArrayList<>());
        this.totalTicketsAdded = 0L;
    }

    /**
     * Retrieves the system configuration.
     * @return The active system configuration
     * @throws RuntimeException if configuration is not found
     */
    private SysConEntity getSysConConfig() {
        return sysConRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("System configuration not found"));
    }

    /**
     * Adds tickets to the pool in a thread-safe manner.
     * Respects the ticket release rate and maximum capacity constraints.
     * @param vendorId The ID of the vendor adding tickets
     */
    public synchronized void addTickets(int vendorId) {
        SysConEntity config = getSysConConfig();
        int releaseRate = config.getTicketReleaseRate();

        while (releaseRate > 0 && totalTicketsAdded < config.getTotalTickets()) {
            try {
                // Check if pool has capacity for more tickets
                if (getPoolSize() < config.getMaxTicketCapacity()) {
                    // Create and add new ticket
                    TicketEntity ticket = new TicketEntity(
                            totalTicketsAdded + 1L,
                            vendorId,
                            LocalDateTime.now()
                    );
                    ticketPool.add(ticket);
                    ticketRepository.save(ticket);

                    totalTicketsAdded++;
                    log.info("Vendor {} added ticket: {}", vendorId, ticket.getTicketId());

                    // Notify waiting threads that a ticket is available
                    notifyAll();
                } else {
                    log.info("Maximum ticket capacity reached. Waiting for space in pool.");
                    wait(); // Wait for space in the pool
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted while adding tickets: {}", e.getMessage());
                break;
            }
            releaseRate--;
        }

        if (totalTicketsAdded >= config.getTotalTickets()) {
            log.info("Total ticket limit reached. No more tickets can be added.");
        }
    }

    /**
     * Removes and assigns tickets to customers in a thread-safe manner.
     * Respects the customer retrieval rate constraint.
     * @param customerId The ID of the customer purchasing tickets
     */
    public synchronized void removeTicket(int customerId) {
        SysConEntity config = getSysConConfig();
        int retrievalRate = config.getCustomerRetrievalRate();

        while (retrievalRate > 0 && purchasedTickets.size() < config.getTotalTickets()) {
            try {
                if (!ticketPool.isEmpty()) {
                    // Remove and assign ticket to customer
                    TicketEntity purchasedTicket = ticketPool.remove(0);
                    purchasedTicket.setCustomerId(customerId);
                    purchasedTicket.setTimePurchased(LocalDateTime.now());

                    // Save to repository and add to purchased list
                    ticketRepository.save(purchasedTicket);
                    purchasedTickets.add(purchasedTicket);

                    log.info("Customer {} purchased ticket: {}", customerId, purchasedTicket.getTicketId());

                    // Notify waiting threads that space is available in the pool
                    notifyAll();
                } else {
                    log.info("No tickets available. Customer {} is waiting.", customerId);
                    wait(); // Wait for tickets to become available
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Thread interrupted while purchasing tickets: {}", e.getMessage());
                break;
            }
            retrievalRate--;
        }

        if (purchasedTickets.size() >= config.getTotalTickets()) {
            log.info("All tickets have been purchased.");
        }
    }

    /**
     * Public interface to start the ticket addition process.
     * Validates vendor existence before proceeding.
     * @param vendorId The ID of the vendor
     * @throws RuntimeException if vendor doesn't exist
     */
    public void startAddingTickets(int vendorId) {
        if (vendorRepository.existsByVendorId(vendorId)) {
            addTickets(vendorId);
        } else {
            throw new RuntimeException("Vendor not found with ID: " + vendorId);
        }
    }

    /**
     * Public interface to start the ticket purchasing process.
     * Validates customer existence before proceeding.
     * @param customerId The ID of the customer
     * @throws RuntimeException if customer doesn't exist
     */
    public void startPurchasingTickets(int customerId) {
        if (customerRepository.existsById(customerId)) {
            removeTicket(customerId);
        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }

    /**
     * Retrieves all purchased tickets from the repository.
     */
    public List<TicketEntity> getPurchasedTickets() {
        return ticketRepository.findAllByCustomerIdIsNotNull();
    }

    /**
     * Retrieves current tickets in the pool.
     */
    public List<TicketEntity> getTicketsInPool() {
        return new ArrayList<>(ticketPool);
    }

    /**
     * Gets the current size of the ticket pool.
     */
    public synchronized Integer getPoolSize() {
        return ticketPool.size();
    }

    /**
     * Gets the total number of tickets added across all sessions.
     */
    public synchronized Long getTotalTicketsAdded() {
        return totalTicketsAdded;
    }

    /**
     * Checks if all available tickets have been added to the system.
     */
    public synchronized boolean isAllTicketsAdded() {
        SysConEntity config = getSysConConfig();
        return totalTicketsAdded >= config.getTotalTickets();
    }

    /**
     * Checks if all tickets have been purchased.
     */
    public synchronized boolean isAllTicketsPurchased() {
        SysConEntity config = getSysConConfig();
        return purchasedTickets.size() >= config.getTotalTickets();
    }
}