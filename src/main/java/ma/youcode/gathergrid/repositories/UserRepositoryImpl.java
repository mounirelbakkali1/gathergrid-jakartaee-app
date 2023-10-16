package ma.youcode.gathergrid.repositories;

import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import ma.youcode.gathergrid.domain.User;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Priority(1)
public class UserRepositoryImpl implements UserRepository {


    private final EntityManager em;

    public UserRepositoryImpl() {
        EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("default");
        em = emf.createEntityManager();
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
