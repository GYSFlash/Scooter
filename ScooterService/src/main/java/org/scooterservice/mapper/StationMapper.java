package org.scooterservice.mapper;

import org.mapstruct.Mapper;
import org.scooterservice.dto.StationDto;
import org.scooterservice.entity.Station;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StationMapper {
    StationDto toDto(Station station);
    Station toStation(StationDto stationDto);
    List<StationDto> toStationDtos(List<Station> stations);
}
