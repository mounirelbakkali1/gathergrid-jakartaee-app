package ma.youcode.gathergrid.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import ma.youcode.gathergrid.config.MyQualifier;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.Ticket;
import ma.youcode.gathergrid.domain.TicketPack;
import ma.youcode.gathergrid.dto.TicketDto;
import ma.youcode.gathergrid.mapper.TicketMapper;
import ma.youcode.gathergrid.repositories.TicketRepository;
import ma.youcode.gathergrid.utils.Response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@RequestScoped
@NoArgsConstructor
@Transactional
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


    public Response<TicketDto> save(Ticket ticket) {
        // TODO : check if the user is not the event creator !!!!!!
        if (ticket.getEvent().getOrganization().getUser().getUsername().equals(ticket.getUser().getUsername())){
            return Response
                    .<TicketDto>builder()
                    .error(List.of(new Error("Can't reserve a ticket for your own event")))
                    .build();
        }
        // TODO :  check if the event exists
        if (ticket.getEvent()==null){
            return Response
                    .<TicketDto>builder()
                    .error(List.of(new Error("Event not found")))
                    .build();
        }
        // TODO : check if the quantity is available in the event
        TicketPack pack = ticket.getEvent().getTicketPacks()
                .stream()
                .filter(ticketPack -> ticketPack.getTicketType().equals(ticket.getTicketType()))
                .findFirst()
                .get();
        if(pack.getQuantity() <= 0){
            return Response
                    .<TicketDto>builder()
                    .error(List.of(new Error("Not enough tickets available for this type")))
                    .build();
        }
        // TODO : check if the date is valid
        if (canBuyOrCancel(ticket, ticket.getEvent().getDate())){
            return Response
                    .<TicketDto>builder()
                    .error(List.of(new Error("Can't reserve a ticket after the event date")))
                    .build();
        }
        // TODO : set the price of the ticket corresponding to the ticket type
        ticket.setPrice(pack.getPrice());
        ticket.setQuantity(1);
        ticketRepository.save(ticket);
        eventService.updateQuantityOfTicket(ticket.getEvent(),ticket.getTicketType(),false);
        return Response
                .<TicketDto>builder()
                .result(ticketMapper.toDto(ticket))
                .build();

    }
    public Response<TicketDto> cancel(Ticket ticket) {
        // TODO : can cancel
        if(canBuyOrCancel(ticket, ticket.getReservationDate())){
            ticketRepository.cancel(ticket);
            eventService.updateQuantityOfTicket(ticket.getEvent(),ticket.getTicketType(),true);
            return Response
                    .<TicketDto>builder()
                    .result(ticketMapper.toDto(ticket))
                    .build();
        }else{
            return Response
                    .<TicketDto>builder()
                    .error(List.of(new Error("Can't cancel a ticket after the event date")))
                    .build();
        }
    }

    private boolean canBuyOrCancel(Ticket ticket, Date eventDate) {
        return Date.from(Instant.now()).after(eventDate);
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

    public Optional<Ticket> findById(Long ticketId) {
        return ticketRepository.findById(ticketId);
    }
}
