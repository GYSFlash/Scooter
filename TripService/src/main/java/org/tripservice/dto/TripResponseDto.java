package org.tripservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;
@Data
public class TripResponseDto {
    @NotNull
    private BigDecimal price;
    @NotNull
    private double minutes;
    @NotNull
    private double distance;
    @NotNull
    private ScooterResponseDto scooter;
    @NotNull
    private UserResponseDto user;
    @NotNull
    private StationDto startStation;
    @NotNull
    private StationDto endStation;
}
