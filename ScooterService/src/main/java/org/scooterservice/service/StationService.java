package org.scooterservice.service;

import org.scooterservice.dto.StationDto;

import java.util.List;
import java.util.UUID;

public interface StationService {
    void addStation(StationDto stationDto);
    void deleteStationById(UUID id);
    void updateStation(StationDto stationDto, UUID id);
    StationDto getStationById(UUID id);
    List<StationDto> getAllStations();
    int countScootersByStation(UUID id);
    List<StationDto> nearStation(double userLat, double userLon);
    String routeForNearStation(double userLat, double userLon);
}
