package org.scooterservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
@Table(name = "scooter")
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "model")
    private String model;
    @Column(name = "number", unique = true)
    private String number;
    @Column(name = "distance")
    private double totalDistance;
    @Column(name = "battery")
    private int battery;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ScooterStatus status;
    @OneToOne
    @JoinColumn(name = "station_id")
    private Station station;

    public enum ScooterStatus {
        FREE, IN_USE, REPAIRING
    }
    public Scooter(String model, String number, int battery, Station station) {
        this.model = model;
        this.number = number;
        this.totalDistance = 0;
        this.battery = battery;
        this.station = station;
        this.status = ScooterStatus.FREE;
    }
}
