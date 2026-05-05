package org.tripservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.tripservice.dto.ScooterResponseDto;
import org.tripservice.dto.StationDto;

import java.util.UUID;

@FeignClient(name = "scooter-service")
public interface ScooterClient {
    @GetMapping("/scooter/{id}")
    ScooterResponseDto getScooterById(@PathVariable("id") UUID id);
    @GetMapping("/station/{id}")
    StationDto getStationById(@PathVariable("id") UUID id);
}
