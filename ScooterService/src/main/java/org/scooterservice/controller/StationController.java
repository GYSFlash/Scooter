package org.scooterservice.controller;

import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scooterservice.dto.StationDto;
import org.scooterservice.service.StationService;
import org.scooterservice.service.StationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/station")
public class StationController {
    private static final Logger log = LogManager.getLogger(StationController.class);
    private StationService service;

    public StationController(StationServiceImpl service) {
        this.service = service;
    }
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> addStation(@Valid @RequestBody StationDto station){
        log.info("POST /station");
        service.addStation(station);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStation(@PathVariable UUID id){
        log.info("DELETE /station");
        service.deleteStationById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER','EMPLOYEE')")
    public StationDto getStationById(@PathVariable UUID id){
        log.info("GET /station");
        return service.getStationById(id);
    }
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER','EMPLOYEE')")
    public List<StationDto> getAllStations(){
        log.info("GET /station");
        return service.getAllStations();
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateStation(@PathVariable UUID id,@Valid  @RequestBody StationDto station){
        log.info("PUT /station");
        service.updateStation(station,id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/count/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public int countScootersByStation(@PathVariable UUID id){
        log.info("GET /station/count");
        return service.countScootersByStation(id);
    }
    @GetMapping("/near/{lat}/{lon}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<StationDto> getNearStations(@PathVariable double lat, @PathVariable double lon){
        log.info("GET /station/near");
        return service.nearStation(lat,lon);
    }
    @GetMapping("/route/{lat}/{lon}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public String routeForNearStation(@PathVariable double lat, @PathVariable double lon){
        log.info("GET /station/route");
        return service.routeForNearStation(lat,lon);
    }

}
