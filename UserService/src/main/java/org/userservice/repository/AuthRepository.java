package org.userservice.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.userservice.entity.AuthorizationData;
import org.userservice.entity.User;

import java.util.Optional;

@Repository
public class AuthRepository extends BaseRepository<AuthorizationData,String> {

    public AuthRepository(SessionFactory sessionFactory) {
        super(AuthorizationData.class,sessionFactory);
    }
    @Override
    protected String getId(AuthorizationData authorizationData) {
        return authorizationData.getUsername();
    }
    public Optional<AuthorizationData> findByUsername(String username) {
        return getSession().createQuery("""
                        select a from AuthorizationData a 
                        join fetch a.user
                        where a.username = :username
                        """, AuthorizationData.class
        ).setParameter("username", username).getResultList().stream().findFirst();
    }
}
