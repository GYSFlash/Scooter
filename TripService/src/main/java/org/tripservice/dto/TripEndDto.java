package org.tripservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class TripEndDto {
    @NotNull
    private LocalDateTime endTrip;
    @NotNull
    private BigDecimal price;
    @NotNull
    @Min(1)
    private double distance;
    @NotNull
    private UUID station_to_id;
}
