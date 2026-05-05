package org.userservice.repository;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.userservice.entity.User;

import java.util.Optional;
import java.util.UUID;
@Repository
public class UserRepository extends BaseRepository<User, UUID> {

    public UserRepository(SessionFactory sessionFactory) {
        super(User.class,sessionFactory);
    }
    @Override
    protected UUID getId(User user){
        return user.getId();
    }

    public Optional<User> findByEmail(String email){
        return Optional.ofNullable(getSession().createQuery("""
                from User where email = :email
                """
                ,User.class).setParameter("email",email).getSingleResult());
    }
}
