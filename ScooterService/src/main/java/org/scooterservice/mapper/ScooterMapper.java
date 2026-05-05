package org.scooterservice.mapper;

import org.mapstruct.Mapper;
import org.scooterservice.dto.ScooterRequestDto;
import org.scooterservice.dto.ScooterResponseDto;
import org.scooterservice.entity.Scooter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScooterMapper {
    Scooter toScooter(ScooterRequestDto scooterRequestDto);
    ScooterResponseDto toScooterResponseDto(Scooter scooter);
    List<ScooterResponseDto> toScooterResponseDtos(List<Scooter> scooters);
}
