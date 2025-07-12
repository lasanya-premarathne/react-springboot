package com.example.services;

import com.example.models.entities.VendorEntity;
import com.example.repository.VendorRepository;
import com.example.services.util.RandomFourDigitGenerationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Service class handling business logic for vendor operations.
 * Manages vendor creation, retrieval, and deletion operations.
 */
@Service
@Transactional
public class VendorService {
    private final VendorRepository vendorRepository;
    private final RandomFourDigitGenerationService uniqueIdService;

    public VendorService(RandomFourDigitGenerationService uniqueIdService,
                         VendorRepository vendorRepository) {
        this.uniqueIdService = uniqueIdService;
        this.vendorRepository = vendorRepository;
    }

    /**
     * Retrieves all vendors from the database.
     */
    public List<VendorEntity> getAllVendors() {
        return vendorRepository.findAll();
    }

    /**
     * Retrieves a vendor by their ID.
     */
    public VendorEntity getVendorById(int id) {
        return vendorRepository.findByVendorId(id);
    }

    /**
     * Adds a new vendor or retrieves existing one if name already exists.
     */
    public VendorEntity addVendor(VendorEntity vendorEntity) {
        if (!vendorRepository.existsByVendorName(vendorEntity.getVendorName())) {
            vendorEntity.setVendorId(uniqueIdService.generateUniqueRandomFourDigit());
            return vendorRepository.save(vendorEntity);
        }
        return vendorRepository.findByVendorName(vendorEntity.getVendorName());
    }

    /**
     * Deletes a vendor by their ID if they exist.
     */
    public void deleteVendor(int id) {
        if (vendorRepository.existsByVendorId(id)) {
            vendorRepository.deleteById(id);
            System.out.println("Vendor(" + id + ") has been deleted");
        } else {
            System.out.println("Vendor does not exist");
        }
    }
}