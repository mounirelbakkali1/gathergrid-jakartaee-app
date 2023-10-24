package ma.youcode.gathergrid.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.config.UserDatabase;
import ma.youcode.gathergrid.domain.Role;
import ma.youcode.gathergrid.domain.User;

import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {


    private  EntityManager em;

    @Inject
    public UserRepositoryImpl(@UserDatabase EntityManager em) {
        this.em = em;
    }


    @Override
    @Transactional
    public Optional<User> findByUsername(String username) {
        System.out.println("Getting user by username: " + username);
        return em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream().findAny();
    }

    @Override
    public void save(User user) {
        System.out.println("saving user... " + user);
        Role userRole = em.createQuery("select r from Role r where r.name = :name", Role.class)
                .setParameter("name", "USER")
                .getResultStream().findAny().orElse(null);
        if (userRole == null) {
            userRole = new Role("USER");
            em.persist(userRole);
        }
        user.setRole(userRole);
        em.merge(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        em.merge(user);
        em.flush();
        em.clear();
    }

    @Override
    @Transactional
    public void delete(User user) {
        em.remove(user);
    }

    @Override
    @Transactional
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public Optional<User> findByName(String userName) {
        return em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", userName)
                .getResultStream().findAny();
    }
}
