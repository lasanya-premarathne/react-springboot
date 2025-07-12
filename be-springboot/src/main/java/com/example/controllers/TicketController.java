package com.example.controllers;

import com.example.models.entities.TicketEntity;
import com.example.services.TicketPoolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    private final TicketPoolService ticketPoolService;

    public TicketController(TicketPoolService ticketPoolService) {
        this.ticketPoolService = ticketPoolService;
    }

    @GetMapping("/purchased")
    public ResponseEntity<List<TicketEntity>> getPurchasedTickets() {
        List<TicketEntity> tickets = ticketPoolService.getPurchasedTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/pool")
    public ResponseEntity<List<TicketEntity>> getTicketsInPool() {
        List<TicketEntity> tickets = ticketPoolService.getTicketsInPool();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/vendor/{vendorId}/add")
    public ResponseEntity<Void> startAddingTickets(@PathVariable int vendorId) {
        try {
            ticketPoolService.startAddingTickets(vendorId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/customer/{customerId}/purchase")
    public ResponseEntity<Void> startPurchasingTickets(@PathVariable int customerId) {
        try {
            ticketPoolService.startPurchasingTickets(customerId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getPoolStats() {
        Map<String, Object> stats = Map.of(
                "poolSize", ticketPoolService.getPoolSize(),
                "totalTicketsAdded", ticketPoolService.getTotalTicketsAdded(),
                "allTicketsAdded", ticketPoolService.isAllTicketsAdded(),
                "allTicketsPurchased", ticketPoolService.isAllTicketsPurchased()
        );
        return ResponseEntity.ok(stats);
    }
}