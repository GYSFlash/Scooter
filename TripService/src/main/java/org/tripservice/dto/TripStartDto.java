package org.tripservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
@Data
public class TripStartDto {
    @NotNull
    private LocalDate startDate;
    @NotNull
    private UUID user_id;
    @NotNull
    private UUID scooter_id;
    @NotNull
    private UUID station_from_id;
}
