package com.example.models.workers;

import com.example.models.entities.VendorEntity;
import com.example.services.TicketPoolService;
import lombok.Getter;

@Getter
public class VendorWorker implements Runnable {
    private final VendorEntity vendor;
    private final TicketPoolService ticketPool;

    public VendorWorker(TicketPoolService ticketPool, VendorEntity vendor) {
        this.ticketPool = ticketPool;
        this.vendor = vendor;
    }

    @Override
    public void run() {
        ticketPool.addTickets(vendor.getVendorId());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Vendor thread completed: " + vendor.getVendorName());
    }
}