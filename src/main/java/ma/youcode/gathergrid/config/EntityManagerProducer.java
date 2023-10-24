package ma.youcode.gathergrid.config;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;


@Singleton
public class EntityManagerProducer  {

    private EntityManager em;

    @PersistenceContext(unitName = "primary",type = PersistenceContextType.EXTENDED)
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Produces
    @UserDatabase
    @ApplicationScoped
    public EntityManager produceEntityManager() {
        return em;
    }



    public void close(@Disposes @UserDatabase EntityManager em) {
        if(em.isOpen()) {
            em.close();
        }
    }
}