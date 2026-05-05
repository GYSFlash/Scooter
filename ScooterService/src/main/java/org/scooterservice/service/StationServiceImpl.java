package org.scooterservice.service;

import org.scooterservice.dto.StationDto;
import org.scooterservice.entity.Station;
import org.scooterservice.mapper.StationMapper;
import org.scooterservice.repository.StationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StationServiceImpl implements StationService {
    private StationRepository stationRepository;
    private StationMapper mapper;

    public StationServiceImpl(StationRepository stationRepository, StationMapper mapper) {
        this.stationRepository = stationRepository;
        this.mapper = mapper;
    }

    @Override
    public void addStation(StationDto stationDto) {
        stationRepository.create(mapper.toStation(stationDto));
    }

    @Override
    public void deleteStationById(UUID id) {
        stationRepository.deleteById(id);
    }

    @Override
    public void updateStation(StationDto stationDto, UUID id) {
        Station station = mapper.toStation(stationDto);
        station.setId(id);
        stationRepository.update(station);
    }

    @Override
    public StationDto getStationById(UUID id) {
        return mapper.toDto(stationRepository.findById(id).orElse(null));
    }

    @Override
    public List<StationDto> getAllStations() {
        return mapper.toStationDtos(stationRepository.findAll());
    }

    @Override
    public int countScootersByStation(UUID id) {
        return stationRepository.countScootersByStation(id);
    }

    @Override
    public List<StationDto> nearStation(double userLat, double userLon) {
        double radiusKm = 5;
        return stationRepository.findAll().stream()
                .filter(station -> haversineDistance(userLat, userLon,
                        station.getLatitude(), station.getLongitude()) <= radiusKm)
                .sorted(Comparator.comparingDouble(station ->
                        haversineDistance(userLat, userLon,
                                station.getLatitude(), station.getLongitude())))
                .map(station -> mapper.toDto(station))
                .collect(Collectors.toList());
    }

    @Override
    public String routeForNearStation(double userLat, double userLon) {
        return stationRepository.findAll().stream()
                .min(Comparator.comparingDouble(station ->
                        haversineDistance(userLat, userLon,
                                station.getLatitude(), station.getLongitude())))
                .map(station -> buildYandexMapsUrl(userLat, userLon,
                        station.getLatitude(), station.getLongitude()))
                .orElseThrow(() -> new RuntimeException("No stations found"));
    }

    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371.0;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    private String buildYandexMapsUrl(double fromLat, double fromLon,
                                      double toLat, double toLon) {
        return String.format(
                "https://yandex.ru/maps/?rtext=%s,%s~%s,%s&rtt=pd",
                fromLat, fromLon, toLat, toLon
        );
    }
}
