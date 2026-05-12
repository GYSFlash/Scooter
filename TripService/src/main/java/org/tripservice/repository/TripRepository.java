package org.tripservice.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.tripservice.entity.Trip;

import java.util.List;
import java.util.UUID;

@Repository
public class TripRepository extends BaseRepository<Trip, UUID>{
    public TripRepository(SessionFactory factory){
        super(Trip.class,factory);
    }
    public UUID getId(Trip trip){
        return trip.getId();
    }
    public List<Trip> getTripsByUser(UUID user){
        return getSession().createQuery("""
        from Trip where user_id = :user
             """).setParameter("user",user).list();
    }
    public List<Trip> getTripsByScooter(UUID scooter){
        return getSession().createQuery("""
        from Trip where scooter_id = :scooter
             """).setParameter("scooter",scooter).list();
    }
    public int countTripsByUser(UUID user){
        Long count = getSession().createQuery("""
                select count(*) from Trip where user_id = :user
                """, Long.class)
                .setParameter("user", user)
                .uniqueResult();
        return count.intValue();
    }
}
