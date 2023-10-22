package ma.youcode.gathergrid.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.config.UserDatabase;
import ma.youcode.gathergrid.domain.Organization;

import java.util.List;
import java.util.Optional;

@RequestScoped
public class OrganizationRepository {

    private  EntityManager em ;

    @Inject
    public OrganizationRepository(@UserDatabase EntityManager em) {
        this.em = em;
    }

    public OrganizationRepository() {
    }

    public List<Organization> findAllOrganizations(){
        return em.createQuery("select o from Organization o",Organization.class).getResultList();
    }

    public Optional<Organization> findOrganizationById(Long id){
        return Optional.of(em.find(Organization.class,id));
    }

    public Organization saveOrganization(Organization organization){
        em.persist(organization);
        return organization;
    }
    public Organization updateOrganization(Organization organization){
        em.merge(organization);
        return organization;
    }
    public void deleteOrganization(Organization organization){
        em.merge(organization);
        em.remove(organization);
    }

    public Optional<Organization> findOrganizationByName(String name){
        return em.createQuery("select o from Organization o where lower(o.name) = :name",Organization.class)
                    .setParameter("name",name)
                    .getResultStream().findAny();
    }

    public List<Organization> findAllOrganizationsByUser(Long id) {
        return em.createQuery("select o from Organization o where o.user.id = :userId", Organization.class)
                .setParameter("userId", id)
                .getResultList();
    }
}
