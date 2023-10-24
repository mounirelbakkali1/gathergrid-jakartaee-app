package ma.youcode.gathergrid.repositories;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.config.UserDatabase;
import ma.youcode.gathergrid.domain.Event;

import java.util.List;
import java.util.Optional;

@Transactional
public class EventRepositoryImpl implements EventRepository{

    private EntityManager em;
    @Inject
    public EventRepositoryImpl(@UserDatabase EntityManager em) {
        this.em = em;
    }

    @Override
    public Event findById(long id) {
        return em.find(Event.class,id);
    }

    @Override
    public void save(Event event) {
        em.persist(event);
    }

    @Override
    public void update(Event event) {
        em.merge(event);
        em.flush();
    }

    @Override
    public void delete(Event event) {
        em.remove(em.contains(event) ? event : em.merge(event));
    }

    @Override
    public List<Event> findAll() {
        return em.createQuery("Select e from Event e order by e.date desc", Event.class).getResultList();
    }

    @Override
    public Optional<Event> findEventByName(String eventName) {
        return em.createQuery("select e from Event e where e.name = :eventName",Event.class)
                .setParameter("eventName",eventName)
                .getResultStream().findAny();
    }
}
