package ma.youcode.gathergrid.repositories;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import ma.youcode.gathergrid.domain.User;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Priority(1)
public class UserRepositoryImpl implements UserRepository {

    @Inject
    private EntityManager em;

    public UserRepositoryImpl() {
    }


    @Override
    public Optional<User> findByUsername(String username) {
        System.out.println("Getting user by username: " + username);
        return em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream().findAny();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }
}
