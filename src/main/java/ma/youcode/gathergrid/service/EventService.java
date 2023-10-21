package ma.youcode.gathergrid.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.repositories.EventRepository;
import ma.youcode.gathergrid.utils.Response;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        }else if(event.getCategory() == null || event.getOrganization() == null){
            this.errors.add(new Error("Invalid Category or organization"));
        }else if( event.getNumberOfTicketsAvailable() < 10) this.errors.add(new Error("Invalid Number of places"));
    }

    public Response<List<Event>> getAllEvents(){
        Response<List<Event>> eventResponse = new Response<>();
        eventResponse.setResult(eventRepository.findAll());
        return eventResponse;
    }

    public Optional<Event> getEventByName(String eventName) {
        return eventRepository.findEventByName(eventName);
    }

    public Optional<Event> getEventById(Long eventId) {
        return Optional.ofNullable(eventRepository.findById(eventId));
    }

    public Response<Event> updateEvent(Event event) {
        Response<Event> eventResponse = new Response<>();
        Optional<Event> optionalEvent =  getEventById(event.getId());
        if(optionalEvent.isPresent()){
            validate(event);
            if(!this.errors.isEmpty()) eventResponse.setError(this.errors);
            else {
                eventRepository.update(event);
                eventResponse.setResult(event);
            }
        }
        return eventResponse;
    }
}
