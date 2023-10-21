package ma.youcode.gathergrid.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TicketDto {
    private float price;
    private int quantity;
    private String ticketType;
    private String eventName;
    private String username;
    private String reservationDate;
    private LocalDateTime eventDate;
}
