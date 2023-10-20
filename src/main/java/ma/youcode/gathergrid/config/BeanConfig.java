package ma.youcode.gathergrid.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import ma.youcode.gathergrid.repositories.TicketRepository;
import ma.youcode.gathergrid.repositories.TicketRepositoryImpl;
import ma.youcode.gathergrid.service.IOrganizationService;
import ma.youcode.gathergrid.service.OrganizationService;

@ApplicationScoped
public class BeanConfig {


    @Produces
    @Default
    public IOrganizationService organizationService(){
        return new OrganizationService();
    }

    @Produces
    @Default
    public TicketRepository ticketRepository(){
        return new TicketRepositoryImpl();
    }
}
