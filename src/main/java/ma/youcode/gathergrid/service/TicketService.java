package ma.youcode.gathergrid.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import ma.youcode.gathergrid.config.MyQualifier;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.Ticket;
import ma.youcode.gathergrid.dto.TicketDto;
import ma.youcode.gathergrid.mapper.TicketMapper;
import ma.youcode.gathergrid.repositories.TicketRepository;
import ma.youcode.gathergrid.utils.Response;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequestScoped
@NoArgsConstructor
public class TicketService  {
    private TicketRepository ticketRepository;
    private TicketMapper ticketMapper;
    private EventService eventService;
    @Inject
    public TicketService(@MyQualifier TicketRepository ticketRepository, TicketMapper ticketMapper, EventService eventService) {
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
        this.eventService = eventService;
    }


    public Response<TicketDto> save(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        // TODO :  check if the event exists
        Optional<Event> eventByName = eventService.getEventByName(ticketDto.getEventName());
        if (eventByName.isEmpty()){
            return Response
                    .<TicketDto>builder()
                    .error(List.of(new Error("Event not found")))
                    .build();
        }
        // TODO : check if the quantity is available in the event
        Event event = eventByName.get();
        if(ticketDto.getQuantity() > event.getNumberOfTicketsAvailable()){
            return Response
                    .<TicketDto>builder()
                    .error(List.of(new Error("Not enough tickets available")))
                    .build();
        }
        // TODO : check if the required ticket type is available in the event
        // TODO : check if the date is valid
        if (canBuyOrCancel(ticket, event.getDate())){
            return Response
                    .<TicketDto>builder()
                    .error(List.of(new Error("Can't reserve a ticket after the event date")))
                    .build();
        }
        ticketRepository.save(ticket);
        return Response
                .<TicketDto>builder()
                .result(ticketDto)
                .build();

    }
    public Response<TicketDto> cancel(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        // TODO : can cancel
        if(canBuyOrCancel(ticket, ticket.getReservationDate())){
            ticketRepository.cancel(ticket);
            return Response
                    .<TicketDto>builder()
                    .result(ticketDto)
                    .build();
        }
        return Response
                .<TicketDto>builder()
                .result(ticketDto)
                .build();
    }

    private boolean canBuyOrCancel(Ticket ticket, Date eventDate) {
        return ticket.getReservationDate().after(eventDate);
    }

    public List<TicketDto> findByDate(String date) {
        return ticketRepository.findByDate(date)
                .stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<TicketDto> findByUser(String username) {
        return ticketRepository.findByUser(username)
                .stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<TicketDto> findByEvent(String eventName) {
        return ticketRepository.findByEvent(eventName)
                .stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<TicketDto> findByOrganization(String organizationName) {
        return ticketRepository.findByOrganization(organizationName)
                .stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<TicketDto> findByTicketsType(String ticketType) {
        return ticketRepository.findByTicketsType(ticketType)
                .stream()
                .map(ticketMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }
}
