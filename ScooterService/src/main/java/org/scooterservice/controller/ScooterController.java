package org.scooterservice.controller;

import org.scooterservice.dto.ScooterRequestDto;
import org.scooterservice.dto.ScooterResponseDto;
import org.scooterservice.entity.Scooter;
import org.scooterservice.service.ScooterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/scooter")
public class ScooterController {
    private ScooterService service;

    public ScooterController(ScooterService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Void> addScooter(@RequestBody ScooterRequestDto scooter) {
        service.addScooter(scooter);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScooter(@PathVariable("id") UUID id) {
        service.deleteScooterById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateScooter(@PathVariable("id") UUID id, @RequestBody ScooterRequestDto scooter) {
        service.updateScooter(scooter, id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}")
    public ScooterResponseDto getScooterById(@PathVariable("id") UUID id) {
        return service.getScooterById(id);
    }
    @GetMapping
    public List<ScooterResponseDto> getAllScooters() {
        return service.getAllScooters();
    }
    @PutMapping("/status/{id}/{status}")
    public ResponseEntity<Void> updateScooterStatus(@PathVariable("id") UUID id, @PathVariable("status") Scooter.ScooterStatus status) {
        service.changeStatus(id, status);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/station/{id}/{stationId}")
    public ResponseEntity<Void> updateScooterStation(@PathVariable("id") UUID id, @PathVariable("stationId") UUID stationId) {
        service.changeStation(id, stationId);
        return ResponseEntity.ok().build();
    }

}
