package ma.youcode.gathergrid.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.config.UserDatabase;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.User;

import java.util.List;
import java.util.Optional;

public class EventRepositoryImpl implements EventRepository{

    private EntityManager em;
    @Inject
    public EventRepositoryImpl(@UserDatabase EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Event> findById(int id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public void save(Event event) {

    }

    @Override
    @Transactional
    public void update(Event event) {

    }

    @Override
    @Transactional
    public void delete(Event event) {

    }

    @Override
    public List<Event> findAll(Event event) {
        return null;
    }
}
