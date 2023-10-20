package ma.youcode.gathergrid.repositories;

import ma.youcode.gathergrid.domain.Ticket;

import java.util.List;

public interface TicketRepository {
    public void save(Ticket ticket);
    public void cancel(Ticket ticket);
    public List<Ticket> findByDate(String date);
    public List<Ticket> findByUser(String username);
    public List<Ticket> findByEvent(String eventName);
    public List<Ticket> findByOrganization(String organizationName);
    public List<Ticket> findByTicketsType(String ticketType);
    public List<Ticket> findAll();

}
