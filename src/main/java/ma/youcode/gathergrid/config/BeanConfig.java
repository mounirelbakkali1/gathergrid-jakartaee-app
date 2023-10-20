package ma.youcode.gathergrid.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Qualifier;
import ma.youcode.gathergrid.repositories.TicketRepository;
import ma.youcode.gathergrid.repositories.TicketRepositoryImpl;
import ma.youcode.gathergrid.service.IOrganizationService;
import ma.youcode.gathergrid.service.OrganizationService;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@ApplicationScoped
public class BeanConfig {


    @Produces
    @MyQualifier
    public IOrganizationService organizationService(){
        return new OrganizationService();
    }

    @Produces
    @MyQualifier
    public TicketRepository ticketRepository(){
        return new TicketRepositoryImpl();
    }
}

