package ma.youcode.gathergrid.config;


import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class EntityManagerProducer {

    @Produces
    public EntityManager entityManager(){
        return Persistence.createEntityManagerFactory("default").createEntityManager();
    }
}