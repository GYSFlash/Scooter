package org.tripservice.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.tripservice.dto.TripEndDto;
import org.tripservice.dto.TripResponseDto;
import org.tripservice.dto.TripStartDto;
import org.tripservice.service.TripService;
import org.tripservice.service.TripServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {
    private static final Logger log = LogManager.getLogger(TripController.class);
    private TripServiceImpl tripService;
    public TripController(TripServiceImpl tripService) {
        this.tripService = tripService;
    }
    @GetMapping("/price/{type}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public BigDecimal getPrice(@PathVariable String type) {
        log.info("GET /trips/price/{type}");
        return tripService.getPrice(type);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TripResponseDto> getAllTrips() {
        log.info("GET /trips");
        return tripService.getAllTrips();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public TripResponseDto getTripById(@PathVariable UUID id) {
        log.info("GET /trips/{id}");
        return tripService.getTripById(id);
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> startTrip(@Valid @RequestBody TripStartDto start) {
        log.info("POST /trips");
        tripService.startTrip(start);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> endTrip(@PathVariable UUID id, @Valid @RequestBody TripEndDto end) {
        log.info("POST /trips/{id}");
        tripService.endTrip(end, id);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteTripById(@PathVariable UUID id) {
        log.info("DELETE /trips/{id}");
        tripService.deleteTripById(id);
        return ResponseEntity.noContent().build();
    }
    /*@PutMapping("/{id}")
    public ResponseEntity<Void> updateTrip(@PathVariable UUID id, @RequestBody TripResponseDto trip) {
        tripService.updateTrip(id, trip);
        return ResponseEntity.ok().build();
    }*/
    @GetMapping("/active")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TripResponseDto> getActiveTrips() {
        log.info("GET /trips/active");
        return tripService.getActiveTrips();
    }
    @GetMapping("/count/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public int getTripCount(@PathVariable UUID id) {
        log.info("GET /trips/count/{id}");
        return tripService.countTripsByUser(id);
    }
    @GetMapping("/user/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<TripResponseDto> getTripsByUser(@PathVariable UUID id) {
        log.info("GET /trips/user/{id}");
        return tripService.getTripsByUser(id);
    }
    @GetMapping("/scooter/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TripResponseDto> getTripsByScooter(@PathVariable UUID id) {
        log.info("GET /trips/scooter/{id}");
        return tripService.getTripsByScooter(id);
    }


}
