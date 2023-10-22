package ma.youcode.gathergrid.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import ma.youcode.gathergrid.domain.Ticket;
import ma.youcode.gathergrid.domain.TicketType;
import ma.youcode.gathergrid.dto.TicketDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@ApplicationScoped
public class TicketMapper {

    public TicketDto toDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.getId())
                .price(ticket.getPrice())
                .quantity(ticket.getQuantity())
                .ticketType(ticket.getTicketType().name())
                .eventName(ticket.getEvent().getName())
                .username(ticket.getUser().getUsername())
                .reservationDate(ticket.getReservationDate().toString())
                .eventDate(LocalDateTime.of(LocalDate.parse(ticket.getEvent().getDate().toString()), LocalTime.parse(ticket.getEvent().getHour().toString())))
                .build();
    }

    public Ticket toEntity(TicketDto ticketDto) {
        return Ticket.builder()
                .price(ticketDto.getPrice())
                .quantity(ticketDto.getQuantity())
                .ticketType(TicketType.valueOf(ticketDto.getTicketType()))
                .build();
    }

}
