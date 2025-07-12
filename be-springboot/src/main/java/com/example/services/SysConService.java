package com.example.services;

import com.example.models.entities.SysConEntity;
import com.example.repository.SysConRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Service class handling business logic for system configuration operations.
 * Manages system configuration retrieval and updates.
 */
@Service
@Transactional
public class SysConService {
    private final SysConRepository sysConRepository;

    public SysConService(SysConRepository sysConRepository) {
        this.sysConRepository = sysConRepository;
    }

    /**
     * Retrieves all system configurations.
     */
    public List<SysConEntity> getSysCon() {
        return sysConRepository.findAll();
    }

    /**
     * Saves or updates a system configuration.
     */
    public SysConEntity saveSysCon(SysConEntity sysConEntity) {
        return sysConRepository.save(sysConEntity);
    }

    /**
     * Retrieves a system configuration by event name.
     * @throws RuntimeException if configuration is not found
     */
    public SysConEntity getByEventName(String eventName) {
        return sysConRepository.findByEventName(eventName)
                .orElseThrow(() -> new RuntimeException("System configuration not found for event: " + eventName));
    }
}