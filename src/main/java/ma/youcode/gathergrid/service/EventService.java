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

    private List<Error> errors = new ArrayList<>();

    @Inject
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    public EventService() {
    }

    public Response<Event> createEvent(Event event){
        Response<Event> eventResponse = new Response<>();
        validate(event);
        if(!this.errors.isEmpty()) eventResponse.setError(this.errors);
        else{
            eventRepository.save(event);
            eventResponse.setResult(event);
        }
        return eventResponse;
    }
    public void validate(Event event){
        if( event.getName().isEmpty() || event.getLocation().isEmpty() || event.getDescription().isEmpty()){
            this.errors.add(new Error("All Fields are required"));
        }else if(event.getCategory().getName() == null || event.getOrganization().getName() == null){
            this.errors.add(new Error("Invalid Category or organization"));
        }
    }

    public Response<List<Event>> getAllEvents(){
        Response<List<Event>> eventResponse = new Response<>();
        eventResponse.setResult(eventRepository.findAll());
        return eventResponse;
    }
}
