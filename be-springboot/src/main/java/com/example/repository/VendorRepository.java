package com.example.repository;

import com.example.models.entities.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing VendorEntity objects.
 * Provides CRUD operations and custom queries for vendor data.
 */
@Repository
public interface VendorRepository extends JpaRepository<VendorEntity, Integer> {

    /**
     * Checks if a vendor exists with the given name.
     * @param name The vendor name to search for
     * @return true if a vendor with the given name exists, false otherwise
     */
    boolean existsByVendorName(String name);

    /**
     * Retrieves a vendor by their name.
     * @param name The name of the vendor to find
     * @return The VendorEntity if found, null otherwise
     */
    VendorEntity findByVendorName(String name);

    /**
     * Retrieves a vendor by their unique identifier.
     * @param vendorId The ID of the vendor to find
     * @return The VendorEntity if found, null otherwise
     */
    VendorEntity findByVendorId(int vendorId);

    /**
     * Checks if a vendor exists with the given ID.
     * @param vendorId The vendor ID to check
     * @return true if a vendor with the given ID exists, false otherwise
     */
    boolean existsByVendorId(int vendorId);
}