package com.example.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    private int customerId;

    @Column(nullable = false)
    private String customerName;

    @Column
    private int totalTicketsPurchased = 0;
}