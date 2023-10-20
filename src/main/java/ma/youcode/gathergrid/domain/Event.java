package ma.youcode.gathergrid.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public int getNumberOfTicketsAvailable(){
        return 10;
    }
}
