package com.example.controllers;

import com.example.models.entities.VendorEntity;
import com.example.services.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vendors")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ResponseEntity<List<VendorEntity>> getAllVendors() {
        List<VendorEntity> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorEntity> getVendorById(@PathVariable int id) {
        try {
            VendorEntity vendor = vendorService.getVendorById(id);
            if (vendor != null) {
                return ResponseEntity.ok(vendor);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<VendorEntity> addVendor(@RequestBody VendorEntity vendor) {
        try {
            VendorEntity newVendor = vendorService.addVendor(vendor);
            return ResponseEntity.ok(newVendor);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable int id) {
        try {
            vendorService.deleteVendor(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}