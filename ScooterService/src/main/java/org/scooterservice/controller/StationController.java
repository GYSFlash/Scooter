package org.scooterservice.controller;

import org.scooterservice.dto.StationDto;
import org.scooterservice.service.StationService;
import org.scooterservice.service.StationServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("/station")
public class StationController {

    private StationService service;

    public StationController(StationServiceImpl service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<Void> addStation(@RequestBody StationDto station){
        service.addStation(station);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable UUID id){
        service.deleteStationById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public StationDto getStationById(@PathVariable UUID id){
        return service.getStationById(id);
    }
    @GetMapping
    public List<StationDto> getAllStations(){
        return service.getAllStations();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStation(@PathVariable UUID id, @RequestBody StationDto station){
        service.updateStation(station,id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/count/{id}")
    public int countScootersByStation(@PathVariable UUID id){
        return service.countScootersByStation(id);
    }
    @GetMapping("/near/{lat}/{lon}")
    public List<StationDto> getNearStations(@PathVariable double lat, @PathVariable double lon){
        return service.nearStation(lat,lon);
    }
    @GetMapping("/route/{lat}/{lon}")
    public String routeForNearStation(@PathVariable double lat, @PathVariable double lon){
        return service.routeForNearStation(lat,lon);
    }

}
