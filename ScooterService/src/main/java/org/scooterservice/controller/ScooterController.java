package org.scooterservice.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scooterservice.dto.ScooterRequestDto;
import org.scooterservice.dto.ScooterResponseDto;
import org.scooterservice.entity.Scooter;
import org.scooterservice.service.ScooterService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/scooter")
public class ScooterController {
    private static final Logger log = LogManager.getLogger(ScooterController.class);
    private ScooterService service;

    public ScooterController(ScooterService service) {
        this.service = service;
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addScooter(@Valid @RequestBody ScooterRequestDto scooter) {
        log.info("POST /scooter");
        service.addScooter(scooter);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteScooter(@PathVariable("id") UUID id) {
        log.info("DELETE /scooter/{}", id);
        service.deleteScooterById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<Void> updateScooter(@PathVariable("id") UUID id, @Valid @RequestBody ScooterRequestDto scooter) {
        log.info("PUT /scooter/{}", id);
        service.updateScooter(scooter, id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','USER')")
    public ScooterResponseDto getScooterById(@PathVariable("id") UUID id) {
        log.info("GET /scooter/{}", id);
        return service.getScooterById(id);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE','USER')")
    public List<ScooterResponseDto> getAllScooters() {
        log.info("GET /scooter");
        return service.getAllScooters();
    }
    @PutMapping("/status/{id}/{status}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<Void> updateScooterStatus(@PathVariable("id") UUID id, @PathVariable("status") Scooter.ScooterStatus status) {
        log.info("PUT /scooter/status/{}/{}", id, status);
        service.changeStatus(id, status);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/station/{id}/{stationId}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<Void> updateScooterStation(@PathVariable("id") UUID id, @PathVariable("stationId") UUID stationId) {
        log.info("PUT /scooter/station/{}/{}", id, stationId);
        service.changeStation(id, stationId);
        return ResponseEntity.ok().build();
    }

}
