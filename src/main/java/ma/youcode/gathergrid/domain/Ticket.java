package ma.youcode.gathergrid.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float price;

    private int quantity;

    @Column(name = "ticket_type")
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @ManyToOne
    private Event event;

    @ManyToOne
    private User user;

    @Column(name = "reservation_date", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date reservationDate;
}
