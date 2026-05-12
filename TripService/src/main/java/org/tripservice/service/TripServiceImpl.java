package org.tripservice.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.tripservice.client.ScooterClient;
import org.tripservice.client.UserClient;
import org.tripservice.dto.TripEndDto;
import org.tripservice.dto.TripResponseDto;
import org.tripservice.dto.TripStartDto;
import org.tripservice.dto.UserResponseDto;
import org.tripservice.entity.Trip;
import org.tripservice.mapper.TripMapper;
import org.tripservice.repository.TripRepository;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TripServiceImpl implements TripService {
    private static final Logger log = LogManager.getLogger(TripServiceImpl.class);
    private TripRepository tripRepository;
    private ScooterClient scooterClient;
    private UserClient userClient;
    private TripMapper mapper;
    private final BigDecimal price_per_km = new BigDecimal(30);
    private final BigDecimal price_per_minute = new BigDecimal("6.23");
    public TripServiceImpl(TripRepository tripRepository, ScooterClient scooterClient, UserClient userClient, TripMapper mapper) {
        this.tripRepository = tripRepository;
        this.scooterClient = scooterClient;
        this.userClient = userClient;
        this.mapper = mapper;
    }
    @Override
    public void startTrip(TripStartDto start) {
        log.info("Начало поездки");
        tripRepository.create(mapper.toTripStart(start));
    }
    @Override
    public void endTrip(TripEndDto end, UUID id) {
        log.info("Конец поездки");
        Trip trip = tripRepository.findById(id).orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.setEndTrip(end.getEndTrip());
        trip.setDistance(end.getDistance());
        UserResponseDto user = userClient.getUserById(trip.getUser_id());
        if(user != null && user.getSubscription() == true){
            trip.setPrice(new BigDecimal(0));
        }
        else if (trip.getCostType().equals(Trip.TripCost.TIME)){
        BigDecimal time = new BigDecimal(ChronoUnit.MINUTES.between(trip.getStartTrip(), end.getEndTrip()));
        trip.setPrice(price_per_minute.multiply(time));
        } else if (trip.getCostType().equals(Trip.TripCost.DISTANCE)){
            BigDecimal distance = new BigDecimal(trip.getDistance());
            trip.setPrice(price_per_km.multiply(distance));
        }
        trip.setStation_to_id(end.getStation_to_id());
        tripRepository.update(trip);
    }
    @Override
    public TripResponseDto getTripById(UUID id) {
        log.info("Получение поездки по id");
        Trip trip = tripRepository.findById(id).orElse(null);
        return getTripResponseDto(trip);
    }
    @Override
    public List<TripResponseDto> getTripsByUser(UUID id) {
        log.info("Получение всех поездок пользователя");
        return tripRepository.getTripsByUser(id).stream()
                .map(this::getTripResponseDto).collect(Collectors.toList());
    }
    @Override
    public List<TripResponseDto> getAllTrips(){
        log.info("Получение всех поездок");
        return mapper.toTripResponseDtoList(tripRepository.findAll());
    }
    @Override
    public List<TripResponseDto> getTripsByScooter(UUID id){
        log.info("Получение всех поездок самоката");
        return tripRepository.getTripsByScooter(id)
                .stream().map(this::getTripResponseDto).collect(Collectors.toList());
    }
    @Override
    public void deleteTripById(UUID id){
        log.info("Удаление поездки по id");
        tripRepository.deleteById(id);
    }
    @Override
    public List<TripResponseDto> getActiveTrips(){
        log.info("Получение активных поездок");
        List<Trip> trips = tripRepository.findAll();
        return mapper.toTripResponseDtoList(trips.stream().filter(trip -> trip.getEndTrip() == null).toList());

    }
    public BigDecimal getPrice(String costType) {
        log.info("Получение цены");
        if(costType.equals("km")) {
            log.info("Расчет цены по километражу");return price_per_km;}
        else if (costType.equals("time")){
            log.info("Расчет цены по времени");
            return price_per_minute;}
        else{
            log.error("Ошибка при расчете цены");
            return null;
    }
    }
    @Override
    public int countTripsByUser(UUID id){
        log.info("Подсчет поездок пользователя");
        return tripRepository.countTripsByUser(id);
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
