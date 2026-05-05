package org.tripservice.service;

import org.tripservice.dto.TripEndDto;
import org.tripservice.dto.TripResponseDto;
import org.tripservice.dto.TripStartDto;

import java.util.List;
import java.util.UUID;

public interface TripService {
    void startTrip(TripStartDto start);
    void endTrip(TripEndDto end);
    TripResponseDto getTripById();
    List<TripResponseDto> getTripsByUser();
    List<TripResponseDto> getAllTrips();
    List<TripResponseDto> getTripsByScooter();
    void deleteTripById(UUID id);
    List<TripResponseDto> getActiveTrips();

}
