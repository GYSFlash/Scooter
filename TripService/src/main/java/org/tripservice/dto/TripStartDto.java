package org.tripservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.tripservice.entity.Trip;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
public class TripStartDto {
    @NotNull
    private LocalDateTime startDate;
    @NotNull
    private Trip.TripCost cost;
    @NotNull
    private UUID user_id;
    @NotNull
    private UUID scooter_id;
    @NotNull
    private UUID station_from_id;
}
