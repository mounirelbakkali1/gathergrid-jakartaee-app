package ma.youcode.gathergrid.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import ma.youcode.gathergrid.config.UserDatabase;
import ma.youcode.gathergrid.domain.Comment;
import ma.youcode.gathergrid.domain.Event;

import java.util.List;

@RequestScoped
public class CommentRepository {

    private EntityManager em ;
    @Inject
    public CommentRepository(@UserDatabase EntityManager em) {
        this.em = em;
    }

    public CommentRepository() {
    }

    public List<Comment> findAllByEvent(Long eventId){
        return em.createQuery("select c from Comment c where c.event.id = :eventId order by c.id desc ", Comment.class)
                .setParameter("eventId", eventId)
                .getResultList();
    }

    public void save(Comment comment) {
        em.persist(comment);
    }

}