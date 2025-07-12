package com.example.controllers;

import com.example.controllers.dto.ApiResponse;
import com.example.models.entities.SysConEntity;
import com.example.services.SysConService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/system-config")
@Tag(name = "System Configuration", description = "APIs for managing system configurations")
public class SysConController {

    private final SysConService sysConService;

    public SysConController(SysConService sysConService) {
        this.sysConService = sysConService;
    }

    @Operation(summary = "Get all system configurations")
    @GetMapping
    public ResponseEntity<ApiResponse<List<SysConEntity>>> getAllConfigs() {
        List<SysConEntity> configs = sysConService.getSysCon();
        return ResponseEntity.ok(ApiResponse.success(configs, "System configurations retrieved successfully"));
    }

    @Operation(summary = "Update system configuration")
    @PostMapping
    public ResponseEntity<ApiResponse<SysConEntity>> updateConfig(@RequestBody SysConEntity config) {
        try {
            SysConEntity updatedConfig = sysConService.saveSysCon(config);
            return ResponseEntity.ok(ApiResponse.success(updatedConfig, "System configuration updated successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Failed to update configuration: " + e.getMessage()));
        }
    }
}