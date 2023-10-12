package ma.youcode.gathergrid.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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


}
