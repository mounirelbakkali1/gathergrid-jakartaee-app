package ma.youcode.gathergrid.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.inject.Inject;
import ma.youcode.gathergrid.domain.Category;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.domain.Ticket;
import ma.youcode.gathergrid.dto.EventDto;
import ma.youcode.gathergrid.mapper.EventDtoMapper;
import ma.youcode.gathergrid.repositories.EventRepository;
import ma.youcode.gathergrid.utils.Response;
import org.jboss.weld.junit.MockBean;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(WeldJunit5Extension.class)
@EnableWeld
class EventServiceTest {

    @Inject
    EventService eventService;

    @WeldSetup
    public static WeldInitiator weld = WeldInitiator.from(EventService.class)
            .addBeans(addMockedRepositoryBean())
            .addBeans(addMockedMapperBean())
            .build();

    @Test
    void should_update_events_with_reserved_tickets_inferior_of_20_and_happen_before_today_and_return_updated() {
        Event event1 = Event.builder().name("event1").dateTime(LocalDateTime.now().plusDays(1)).build();
        Event event2 = Event.builder().name("event2").dateTime(LocalDateTime.now().plusDays(2)).build();
        Event event3 = Event.builder().name("event3").dateTime(LocalDateTime.now().plusDays(3)).build();
        Event event4 = Event.builder().name("event4").dateTime(LocalDateTime.now().minusDays(4)).build();
        Event event5 = Event.builder().name("event5").dateTime(LocalDateTime.now().minusDays(5)).build();

        event1.addTicket(Ticket.builder().build());
        event1.addTicket(Ticket.builder().build());
        event1.addTicket(Ticket.builder().build());

        event2.addTicket(Ticket.builder().build());
        event2.addTicket(Ticket.builder().build());

        event3.addTicket(Ticket.builder().build());
        event3.addTicket(Ticket.builder().build());
        event3.addTicket(Ticket.builder().build());
        event3.addTicket(Ticket.builder().build());

        event4.addTicket(Ticket.builder().build());

        event5.addTicket(Ticket.builder().build());
        event5.addTicket(Ticket.builder().build());

        List<Event> events = Arrays.asList(event1, event2, event3, event4, event5);

        List<EventDto> updatedEventsDto = eventService.updateEvents(events);
        assertNotNull(updatedEventsDto);
        assertFalse(updatedEventsDto.isEmpty());
        assertEquals(2, updatedEventsDto.size());
        assertSame(updatedEventsDto.get(0).getClass(), EventDto.class);
        assertEquals( "event4",updatedEventsDto.get(0).getName());
        assertEquals( "event5",updatedEventsDto.get(1).getName());
    }

    @Test
    void update_events_should_return_qualified_events_with_updated_date(){
        Event event1 = Event.builder().name("event1").dateTime(LocalDateTime.now().minusDays(2)).build();
        event1.addTicket(Ticket.builder().build());
        event1.addTicket(Ticket.builder().build());
        List<EventDto> eventDtos = eventService.updateEvents(Arrays.asList(event1));
        assertEquals(LocalDateTime.now().plusDays(2).getDayOfYear(),eventDtos.get(0).getDate().getDayOfYear());
    }

    @Test
    void should_return_list_of_events_from_db(){
        Response<List<Event>> response = eventService.getAllEvents();
        List<Event> events = response.getResult();
        assertNull(response.getError());
        assertNotNull(events);
        assertFalse(events.isEmpty());
        assertEquals(5, events.size());
        assertSame(events.get(0).getClass(), Event.class);
    }

    @Test
    void  should_return_optional_empty_when_event_does_not_exist(){
        Optional<Event> event = eventService.getEventByName("nonexistingevent");
        assertTrue(event.isEmpty());
    }

    @Test
    void should_return_optional_of_event_when_event_exist(){
        Optional<Event> event = eventService.getEventByName("event1");
        assertTrue(event.isPresent());
        assertEquals("event1", event.get().getName());
    }

    @Test
    void should_return_saving_error_due_to_validation_errors(){
        Event event = Event.builder().build();
        Response<Event> response = eventService.createEvent(event);
        assertNotNull(response.getError());
        assertTrue(!response.getError().isEmpty());
        assertEquals("All Fields are required", response.getError().get(0).getMessage());
        assertEquals("Invalid Category or organization", response.getError().get(1).getMessage());
        assertEquals("Invalid Number of places", response.getError().get(2).getMessage());
    }

    @Test
    void should_save_successfully_the_event(){
        Event event = Event.builder()
                .name("event")
                .category(Category.builder().name("cate").build())
                .description("desc")
                .location("location")
                .organization(Organization.builder().name("org").build())
                .numberOfTicketsAvailable(20)
                .build();
        Response<Event> response = eventService.createEvent(event);
        Event result = response.getResult();
        assertNull(response.getError());
        assertNotNull(result);
        assertSame(Event.class,result.getClass());
        assertNotNull(result.getName());
        assertEquals("event",result.getName());
        assertEquals("desc",result.getDescription());
        assertEquals(20,result.getNumberOfTicketsAvailable());
        assertEquals("cate",result.getCategory().getName());
    }


    @Test
    void should_delete_event(){
         Response<Event> response = eventService.deleteEvent(1);
        assertNull(response.getError());
        assertNotNull(response.getResult());
        assertEquals("event1",response.getResult().getName());
    }

    @Test
    void should_failed_deleting_event(){
        Response<Event> response = eventService.deleteEvent(6);
        assertNotNull(response.getError());
        assertTrue(!response.getError().isEmpty());
        assertEquals("Invalid Event",response.getError().get(0).getMessage());
    }






    private static Bean<?> addMockedRepositoryBean() {
        List<Event> eventList = Arrays.asList(
                Event.builder().name("event1").dateTime(LocalDateTime.now().plusDays(1)).build(),
                Event.builder().name("event2").dateTime(LocalDateTime.now().plusDays(2)).build(),
                Event.builder().name("event3").dateTime(LocalDateTime.now().plusDays(3)).build(),
                Event.builder().name("event4").dateTime(LocalDateTime.now().minusDays(4)).build(),
                Event.builder().name("event5").dateTime(LocalDateTime.now().minusDays(5)).build()
        );
        List<EventDto> eventsDto = List.of(
                EventDto.builder().name("event1").date(LocalDateTime.now().plus(1, ChronoUnit.DAYS)).build(),
                EventDto.builder().name("event2").date(LocalDateTime.now().plus(2, ChronoUnit.DAYS)).build(),
                EventDto.builder().name("event3").date(LocalDateTime.now().plus(3, ChronoUnit.DAYS)).build(),
                EventDto.builder().name("event4").date(LocalDateTime.now().minusDays(4)).build(),
                EventDto.builder().name("event5").date(LocalDateTime.now().minusDays(5)).build()
        );
        EventRepository repository = Mockito.mock(EventRepository.class);
        when(repository.findAll())
                .thenReturn(eventList).thenAnswer(invocation -> invocation.getArgument(0));
        doNothing().when(repository).save(Mockito.any(Event.class));
        doNothing().when(repository).update(Mockito.any(Event.class));
        when(repository.findById(1)).thenReturn(Event.builder().name("event1").dateTime(LocalDateTime.now().plusDays(1)).build()).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(repository.findById(6)).thenReturn(null).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(repository.findEventByName("nonexistingevent")).thenReturn(Optional.empty()).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        when(repository.findEventByName("event1")).thenReturn(Optional.of(eventList.get(0))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        return MockBean.builder()
                .types(EventRepository.class)
                .scope(ApplicationScoped.class)
                .creating(repository).build();
    }

    private static Bean<?> addMockedMapperBean() {
        // TODO: Mocking EventRepository
        return MockBean.builder()
                .types(EventDtoMapper.class)
                .scope(ApplicationScoped.class)
                .creating(
                        when(Mockito.mock(EventDtoMapper.class)
                                .toDto(Mockito.any(Event.class)))
                                .thenCallRealMethod().getMock()
                ).build();
    }





}