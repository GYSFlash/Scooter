package org.tripservice.service;

import org.tripservice.dto.TripEndDto;
import org.tripservice.dto.TripResponseDto;
import org.tripservice.dto.TripStartDto;

import java.util.List;
import java.util.UUID;

public interface TripService {
    void startTrip(TripStartDto start);
    void endTrip(TripEndDto end, UUID id);
    TripResponseDto getTripById(UUID id);
    List<TripResponseDto> getTripsByUser(UUID id);
    List<TripResponseDto> getAllTrips();
    List<TripResponseDto> getTripsByScooter(UUID id);
    void deleteTripById(UUID id);
    List<TripResponseDto> getActiveTrips();
    int countTripsByUser(UUID id);

}
