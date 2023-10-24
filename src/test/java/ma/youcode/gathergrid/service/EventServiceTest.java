package ma.youcode.gathergrid.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.spi.Bean;
import jakarta.inject.Inject;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.Ticket;
import ma.youcode.gathergrid.dto.EventDto;
import ma.youcode.gathergrid.mapper.EventDtoMapper;
import ma.youcode.gathergrid.repositories.EventRepository;
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
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
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
    private static List<Event> eventsList=List.of(
            Event.builder().name("event1").dateTime(LocalDateTime.now().plusDays(1)).build(),
            Event.builder().name("event2").dateTime(LocalDateTime.now().plusDays(2)).build(),
            Event.builder().name("event3").dateTime(LocalDateTime.now().plusDays(3)).build(),
            Event.builder().name("event4").dateTime(LocalDateTime.now().minusDays(4)).build(),
            Event.builder().name("event5").dateTime(LocalDateTime.now().minusDays(5)).build()
    );

    private static Bean<?> addMockedRepositoryBean() {
        return MockBean.builder()
                .types(EventRepository.class)
                .scope(ApplicationScoped.class)
                .creating(
                        Mockito.when(Mockito.mock(EventRepository.class).findAll())
                                .thenReturn(eventsList)
                                .thenAnswer(invocation -> invocation.getArgument(0)).getMock()
                ).build();
    }

    private static Bean<?> addMockedMapperBean() {
        // TODO: Mocking EventRepository
        return MockBean.builder()
                .types(EventDtoMapper.class)
                .scope(ApplicationScoped.class)
                .creating(
                        Mockito.when(Mockito.mock(EventDtoMapper.class)
                                                .toDto(Mockito.any(Event.class)))
                                .thenCallRealMethod().getMock()
                               // .thenAnswer(invocation -> invocation.getArgument(0)).getMock()
                ).build();
    }


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

        List<EventDto> eventsDto = List.of(
                EventDto.builder().name("event1").date(LocalDateTime.now().plus(1, ChronoUnit.DAYS)).build(),
                EventDto.builder().name("event2").date(LocalDateTime.now().plus(2, ChronoUnit.DAYS)).build(),
                EventDto.builder().name("event3").date(LocalDateTime.now().plus(3, ChronoUnit.DAYS)).build(),
                EventDto.builder().name("event4").date(LocalDateTime.now().minusDays(4)).build(),
                EventDto.builder().name("event5").date(LocalDateTime.now().minusDays(4)).build()
        );

        List<Event> events = List.of(event1, event2, event3, event4, event5);

        List<EventDto> updatedEventsDto = eventService.updateEvents(events);
        assertNotNull(updatedEventsDto);
        assertFalse(updatedEventsDto.isEmpty());
        assertEquals(2, updatedEventsDto.size());
        assertSame(updatedEventsDto.get(0).getClass(), EventDto.class);
        assertEquals( "event4",updatedEventsDto.get(0).getName());
        assertEquals( "event5",updatedEventsDto.get(1).getName());
    }

}