package com.example.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "system_configuration")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysConEntity {
    @Id
    private int eventId;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private int totalTickets;

    @Column(nullable = false)
    private int ticketReleaseRate;

    @Column(nullable = false)
    private int customerRetrievalRate;

    @Column(nullable = false)
    private int maxTicketCapacity;
}