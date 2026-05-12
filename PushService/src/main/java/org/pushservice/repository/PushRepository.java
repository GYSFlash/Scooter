package org.pushservice.repository;

import org.hibernate.SessionFactory;
import org.pushservice.entity.Push;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PushRepository extends BaseRepository <Push, UUID> {

    public PushRepository(SessionFactory sessionFactory) {
        super(Push.class, sessionFactory);
    }
    @Override
    public UUID getId(Push entity) {
        return entity.getId();
    }
    public List<Push> findByUserId(UUID userId) {
        return getSession()
                .createQuery("""
                from Push where user_id = :userId
                """, Push.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
