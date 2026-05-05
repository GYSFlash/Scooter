package org.scooterservice.dto;

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
    private int battery;
    @NotNull
    private UUID id;
}
