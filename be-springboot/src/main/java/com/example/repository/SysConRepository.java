package com.example.repository;

import com.example.models.entities.SysConEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repository interface for managing SysConEntity objects.
 * Provides CRUD operations and custom queries for system configuration data.
 */
@Repository
public interface SysConRepository extends JpaRepository<SysConEntity, Integer> {

    /**
     * Finds a system configuration by event name.
     * @param eventName The name of the event
     * @return Optional containing the system configuration if found
     */
    Optional<SysConEntity> findByEventName(String eventName);

    /**
     * Checks if a system configuration exists for a given event.
     * @param eventName The name of the event to check
     * @return true if configuration exists, false otherwise
     */
    boolean existsByEventName(String eventName);
}