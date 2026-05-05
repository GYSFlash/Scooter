package org.tripservice.mapper;

import org.mapstruct.Mapper;
import org.tripservice.dto.TripStartDto;
import org.tripservice.dto.TripResponseDto;
import org.tripservice.entity.Trip;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TripMapper {
    Trip toTripStart(TripStartDto tripRequestDto);
    Trip toTripEnd(TripResponseDto tripResponseDto);
    TripResponseDto toTripResponseDto(Trip trip);
    List<TripResponseDto> toTripResponseDtoList(List<Trip> trips);
}
