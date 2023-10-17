package ma.youcode.gathergrid.repositories;

import ma.youcode.gathergrid.domain.Event;

import java.util.List;
import java.util.Optional;

public interface EventRepository {
    public Event findById(long id);
    public void save(Event event);
    public void update(Event event);
    public void delete(Event event);
    public List<Event> findAll(Event event);
}
