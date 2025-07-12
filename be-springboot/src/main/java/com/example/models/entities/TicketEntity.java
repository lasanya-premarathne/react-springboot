package com.example.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
    @Id
    private Long ticketId;

    @Column(nullable = false)
    private int vendorId;

    @Column(nullable = false)
    private LocalDateTime timeAdded;

    @Column
    private int customerId;

    @Column
    private String movieName;

    @Column
    private LocalDateTime timePurchased;

    public TicketEntity(Long ticketId, int vendorId, LocalDateTime timeAdded) {
        this.ticketId = ticketId;
        this.vendorId = vendorId;
        this.timeAdded = timeAdded;
    }
}