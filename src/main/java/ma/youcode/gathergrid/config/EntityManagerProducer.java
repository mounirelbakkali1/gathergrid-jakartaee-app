package ma.youcode.gathergrid.config;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Model
public class EntityManagerProducer  {

    private EntityManager em;

    @PersistenceContext(unitName = "primary")
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