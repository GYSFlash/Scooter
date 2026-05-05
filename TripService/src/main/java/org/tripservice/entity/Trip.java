package org.tripservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "start_trip")
    private LocalDate startTrip;
    @Column(name = "end_trip")
    private LocalDate endTrip;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "distance")
    private double distance;
    @Column(name = "user_id")
    private UUID user_id;
    @Column(name = "scooter_id")
    private UUID scooter_id;
    @Column(name = "station_from_id")
    private UUID station_from_id;
    @Column(name = "station_to_id")
    private UUID station_to_id;

    public Trip(LocalDate startTrip, UUID user_id, UUID scooter_id, UUID station_from_id) {
        this.startTrip = startTrip;
        this.user_id = user_id;
        this.scooter_id = scooter_id;
        this.station_from_id = station_from_id;
    }
}
