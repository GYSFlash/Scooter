package org.tripservice.service;

import org.springframework.stereotype.Service;
import org.tripservice.client.ScooterClient;
import org.tripservice.client.UserClient;
import org.tripservice.dto.TripEndDto;
import org.tripservice.dto.TripResponseDto;
import org.tripservice.dto.TripStartDto;
import org.tripservice.entity.Trip;
import org.tripservice.mapper.TripMapper;
import org.tripservice.repository.TripRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private TripRepository tripRepository;
    private ScooterClient scooterClient;
    private UserClient userClient;
    private TripMapper mapper;
    public TripServiceImpl(TripRepository tripRepository, ScooterClient scooterClient, UserClient userClient, TripMapper mapper) {
        this.tripRepository = tripRepository;
        this.scooterClient = scooterClient;
        this.userClient = userClient;
        this.mapper = mapper;
    }
    @Override
    public void startTrip(TripStartDto start) {
        tripRepository.create(mapper.toTripStart(start));
    }
    @Override
    public void endTrip(TripEndDto end, UUID id) {

    }
    @Override
    public TripResponseDto getTripById(UUID id) {
        Trip trip = tripRepository.findById(id).orElse(null);
        return getTripResponseDto(trip);
    }
    @Override
    public List<TripResponseDto> getTripsByUser(UUID id) {
        return tripRepository.getTripsByUser(id).stream()
                .map(this::getTripResponseDto).collect(Collectors.toList());
    }
    @Override
    public List<TripResponseDto> getAllTrips(){
        return mapper.toTripResponseDtoList(tripRepository.findAll());
    }
    @Override
    public List<TripResponseDto> getTripsByScooter(UUID id){
        return tripRepository.getTripsByScooter(id)
                .stream().map(this::getTripResponseDto).collect(Collectors.toList());
    }
    @Override
    public void deleteTripById(UUID id){
        tripRepository.deleteById(id);
    }
    @Override
    public List<TripResponseDto> getActiveTrips(){
        List<Trip> trips = tripRepository.findAll();
        return mapper.toTripResponseDtoList(trips.stream().filter(trip -> trip.getEndTrip() == null).toList());

    }
    private TripResponseDto getTripResponseDto(Trip trip) {
        TripResponseDto tripResponseDto = mapper.toTripResponseDto(trip);
        tripResponseDto.setScooter(scooterClient.getScooterById(trip.getScooter_id()));
        tripResponseDto.setUser(userClient.getUserById(trip.getUser_id()));
        tripResponseDto.setStartStation(scooterClient.getStationById(trip.getStation_from_id()));
        tripResponseDto.setEndStation(scooterClient.getStationById(trip.getStation_to_id()));
        return tripResponseDto;
    }
}
