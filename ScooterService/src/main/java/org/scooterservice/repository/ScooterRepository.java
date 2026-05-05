package org.scooterservice.repository;

import org.hibernate.SessionFactory;
import org.scooterservice.entity.Scooter;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ScooterRepository extends BaseRepository<Scooter, UUID>{
    public ScooterRepository(SessionFactory factory){
        super(Scooter.class,factory);
    }
    public UUID getId(Scooter scooter){
        return scooter.getId();
    }
}
