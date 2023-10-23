package ma.youcode.gathergrid.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Time hour;

    private String location;

    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Organization organization;

    @OneToMany
    private List<Ticket> tickets=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TicketPack> ticketPacks=new ArrayList<>();

    @Column(name = "available_tickets")
    private int numberOfTicketsAvailable;





    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", hour=" + hour +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category.getName() +
                ", organiser=" + organization.getName() +
                '}';
    }


    public void addTicket(Ticket ticket){
        this.tickets.add(ticket);
        ticket.setEvent(this);
    }
    public int getTotalOfTicketsAvailable(){
        return ticketPacks
                .stream()
                .reduce(0,(acc, ticketPack) -> acc + ticketPack.getQuantity(), Integer::sum);
    }
}
