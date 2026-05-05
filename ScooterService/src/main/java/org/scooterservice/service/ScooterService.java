package org.scooterservice.service;

import org.scooterservice.dto.ScooterRequestDto;
import org.scooterservice.dto.ScooterResponseDto;
import org.scooterservice.entity.Scooter;

import java.util.List;
import java.util.UUID;

public interface ScooterService {
    void addScooter(ScooterRequestDto scooterRequestDto);
    void deleteScooterById(UUID id);
    void updateScooter(ScooterRequestDto scooterRequestDto, UUID id);
    void changeStatus(UUID id, Scooter.ScooterStatus status);
    void changeStation(UUID id_scooter, UUID id_station);
    ScooterResponseDto getScooterById(UUID id);
    List<ScooterResponseDto> getAllScooters();
}
