package com.example.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vendors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorEntity {
    @Id
    private int vendorId;

    @Column(nullable = false)
    private String vendorName;

    @Column
    private int totalTicketsAdded = 0;
}