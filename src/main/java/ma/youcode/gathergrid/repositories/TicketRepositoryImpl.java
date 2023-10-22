package ma.youcode.gathergrid.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import lombok.NoArgsConstructor;
import ma.youcode.gathergrid.config.MyQualifier;
import ma.youcode.gathergrid.config.UserDatabase;
import ma.youcode.gathergrid.domain.Ticket;

import java.util.List;
import java.util.Optional;

@RequestScoped
@MyQualifier
public class TicketRepositoryImpl implements TicketRepository{
    private EntityManager em ;
    @Inject
    public TicketRepositoryImpl(@UserDatabase EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Ticket ticket) {
        em.persist(ticket);
    }

    @Override
    public void cancel(Ticket ticket) {
        em.merge(ticket);
        em.remove(ticket);
    }

    @Override
    public List<Ticket> findByDate(String date) {
        return em.createQuery("select t from Ticket t where t.reservationDate = :date", Ticket.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Ticket> findByUser(String username) {
        return em.createQuery("select t from Ticket t where t.user.username = :username", Ticket.class)
                .setParameter("username", username)
                .getResultList();
    }

    @Override
    public List<Ticket> findByEvent(String eventName) {
        return em.createQuery("select t from Ticket t where t.event.name = :eventName", Ticket.class)
                .setParameter("eventName", eventName)
                .getResultList();
    }

    @Override
    public List<Ticket> findByOrganization(String organizationName) {
        return em.createQuery("select t from Ticket t where t.event.organization.name = :organizationName", Ticket.class)
                .setParameter("organizationName", organizationName)
                .getResultList();
    }

    @Override
    public List<Ticket> findByTicketsType(String ticketType) {
        return em.createQuery("select t from Ticket t where t.ticketType = :ticketType", Ticket.class)
                .setParameter("ticketType", ticketType)
                .getResultList();
    }

    @Override
    public List<Ticket> findAll() {
        return em.createQuery("select t from Ticket t", Ticket.class)
                .getResultList();
    }

    @Override
    public Optional<Ticket> findById(Long ticketId) {
        return Optional.ofNullable(em.find(Ticket.class, ticketId));
    }
}
