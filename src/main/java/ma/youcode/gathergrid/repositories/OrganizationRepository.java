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

    public Organization findOrganizationById(Long id){
        return em.find(Organization.class,id);
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Organization saveOrganization(Organization organization){
        em.persist(organization);
        return organization;
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public Organization updateOrganization(Organization organization){
        em.merge(organization);
        return organization;
    }
    @Transactional(Transactional.TxType.REQUIRED)
    public void deleteOrganization(Organization organization){
        em.remove(organization);
    }

    public Optional<Organization> findOrganizationByName(String name){
        return em.createQuery("select o from Organization o where o.name = :name",Organization.class)
                    .setParameter("name",name)
                    .getResultStream().findAny();
    }
}
