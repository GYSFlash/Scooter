package org.tripservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StationDto {
    @NotNull
    private String name;
    @NotNull
    private double latitude;
    @NotNull
    private double longitude;
}
