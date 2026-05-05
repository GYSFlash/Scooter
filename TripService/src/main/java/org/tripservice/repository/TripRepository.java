package org.tripservice.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.tripservice.entity.Trip;

import java.util.UUID;

@Repository
public class TripRepository extends BaseRepository<Trip, UUID>{
    public TripRepository(SessionFactory factory){
        super(Trip.class,factory);
    }
    public UUID getId(Trip trip){
        return trip.getId();
    }
}
