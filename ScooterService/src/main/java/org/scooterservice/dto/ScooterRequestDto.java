package org.scooterservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;
@Data
public class ScooterRequestDto {
    @NotNull
    private String model;
    @NotNull
    private String number;
    @NotNull
    @Min(0)
    @Max(100)
    private int battery;
    @NotNull
    private UUID id;
}
