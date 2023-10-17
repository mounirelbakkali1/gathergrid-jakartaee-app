package ma.youcode.gathergrid.service;

import jakarta.inject.Inject;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.repositories.EventRepository;
import ma.youcode.gathergrid.utils.Response;

import java.util.List;

public class EventService {
    private EventRepository eventRepository;

    @Inject
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public EventService() {
    }

    public Response<Event> createEvent(Event event){
        //if(event)
        // new Response ;

        //Response<Event> eventResponse = new Response<>();
        //eventResponse.setError(List.of());
        //return eventResponse;

        //eventRepository.save(event);
        //return event;
        return null;
    }
}
