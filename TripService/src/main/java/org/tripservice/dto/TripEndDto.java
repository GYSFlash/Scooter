package org.tripservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
@Data
public class TripEndDto {
    @NotNull
    private LocalDate endTrip;
    @NotNull
    private BigDecimal price;
    @NotNull
    private double distance;
}
