package org.scooterservice.service;

import jakarta.ws.rs.NotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.scooterservice.dto.ScooterRequestDto;
import org.scooterservice.dto.ScooterResponseDto;
import org.scooterservice.entity.Scooter;
import org.scooterservice.entity.Station;
import org.scooterservice.mapper.ScooterMapper;
import org.scooterservice.repository.ScooterRepository;
import org.scooterservice.repository.StationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class ScooterServiceImpl implements ScooterService {
    private static final Logger log = LogManager.getLogger(ScooterServiceImpl.class);
    private ScooterRepository scooterRepository;
    private StationRepository stationRepository;
    private ScooterMapper mapper;

    public ScooterServiceImpl(ScooterRepository scooterRepository, StationRepository stationRepository, ScooterMapper mapper) {
        this.scooterRepository = scooterRepository;
        this.stationRepository = stationRepository;
        this.mapper = mapper;
    }
    @Override
    public void addScooter(ScooterRequestDto scooter) {
        log.info("Добавление нового самоката");
        scooterRepository.create(mapper.toScooter(scooter));
    }
    @Override
    public void deleteScooterById(UUID id) {
        log.info("Удаление самоката");
        scooterRepository.deleteById(id);
    }
    @Override
    public void updateScooter(ScooterRequestDto scooter, UUID id) {
        log.info("Обновление самоката");
        if(getScooterById(id) == null){
            throw new NotFoundException("Самокат не найден");
        }
        Scooter newScooter = mapper.toScooter(scooter);
        newScooter.setId(id);
        scooterRepository.update(newScooter);
    }
    @Override
    public ScooterResponseDto getScooterById(UUID id) {
        log.info("Получение самоката по id");
        return mapper.toScooterResponseDto(scooterRepository.findById(id).orElse(null));
    }
    @Override
    public void changeStatus(UUID id, Scooter.ScooterStatus status){
        log.info("Изменение статуса самоката");
        Scooter scooter = scooterRepository.findById(id).orElse(null);
        scooter.setStatus(status);
        scooterRepository.update(scooter);
    }
    @Override
    public List<ScooterResponseDto> getAllScooters() {
        log.info("Получение всех самокатов");
        return mapper.toScooterResponseDtos(scooterRepository.findAll());
    }
    @Override
    public void changeStation(UUID id, UUID stationId){
        log.info("Изменение локации самоката");
        Scooter scooter = scooterRepository.findById(id).orElse(null);
        Station station = stationRepository.findById(stationId).orElse(null);
        scooter.setStation(station);
        scooterRepository.update(scooter);
    }
}
