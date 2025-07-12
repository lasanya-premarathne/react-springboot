package com.example.models.workers;

import com.example.models.entities.CustomerEntity;
import com.example.services.TicketPoolService;
import lombok.Getter;

@Getter
public class CustomerWorker implements Runnable {
    private final CustomerEntity customer;
    private final TicketPoolService ticketPool;

    public CustomerWorker(TicketPoolService ticketPool, CustomerEntity customer) {
        this.ticketPool = ticketPool;
        this.customer = customer;
    }

    @Override
    public void run() {
        ticketPool.removeTicket(customer.getCustomerId());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Customer thread completed: " + customer.getCustomerName());
    }
}