package org.scooterservice.repository;

import org.hibernate.SessionFactory;
import org.scooterservice.entity.Scooter;
import org.scooterservice.entity.Station;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class StationRepository extends BaseRepository<Station,UUID>{
    public StationRepository(SessionFactory factory){
        super(Station.class,factory);
    }
    public UUID getId(Station station){
        return station.getId();
    }
    public int countScootersByStation(UUID id){
        return getSession().createQuery("""
                        select count(*) from Scooter where station = :id
""").setParameter("id",id).uniqueResult().hashCode();
    }
}
