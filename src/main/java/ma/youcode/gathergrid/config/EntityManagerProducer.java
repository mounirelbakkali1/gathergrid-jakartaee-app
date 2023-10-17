package ma.youcode.gathergrid.config;


import jakarta.enterprise.context.ConversationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;


@ConversationScoped
public class EntityManagerProducer implements Serializable {

    private static final long serialVersionUID = 1L;

    private EntityManager em;

    @PersistenceContext(unitName = "primary")
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Produces
    @UserDatabase
    public EntityManager produceEntityManager() {
        return em;
    }



    public void close(@Disposes @UserDatabase EntityManager em) {
        if(em.isOpen()) {
            em.close();
        }
    }
}