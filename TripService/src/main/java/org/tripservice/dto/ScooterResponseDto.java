package org.tripservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScooterResponseDto {
    @NotNull
    private String model;
    @NotNull
    private String number;
    @NotNull
    private double totalDistance;
    @NotNull
    private int battery;
    @NotNull
    private StationDto station;
}
