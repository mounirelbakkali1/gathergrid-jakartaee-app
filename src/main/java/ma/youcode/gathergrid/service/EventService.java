package ma.youcode.gathergrid.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.repositories.EventRepository;
import ma.youcode.gathergrid.utils.Response;

import java.util.ArrayList;
import java.util.List;
@RequestScoped
public class EventService {
    private EventRepository eventRepository;

    @Inject
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public EventService() {
    }

    public Response<Event> createEvent(Event event){
        Response<Event> eventResponse = new Response<>();
        ArrayList<Error>  errors = new ArrayList<Error>() ;
        if(event.getName().isEmpty() || event.getLocation().isEmpty() || event.getDescription().isEmpty() ){
            errors.add(new Error("All Fields are required"));
            eventResponse.setError(errors);
        }else{
            eventRepository.save(event);
            eventResponse.setResult(event);
        }
        return eventResponse;
    }

    public Response<List<Event>> getAllEvents(){
        Response<List<Event>> eventResponse = new Response<>();
        eventResponse.setResult(eventRepository.findAll());
        return eventResponse;
    }
}
