package ma.youcode.gathergrid.service;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.repositories.OrganizationRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Transactional
public class OrganizationService implements IOrganizationService {

    private  OrganizationRepository organizationRepository;

    public OrganizationService() {
    }

    @Inject
    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public boolean validOrganizationName(String name){
        return organizationRepository.findOrganizationByName(name).isEmpty();
    }

    @Override
    public Optional<Organization> findOrganizationByName(String name){
        return organizationRepository.findOrganizationByName(name);
    }

    @Override
    public List<Organization> getAllOrganizations(){
        return organizationRepository.findAllOrganizations();
    }

    @Override
    public Organization saveOrganization(Organization organization) {
        Objects.requireNonNull(organization.getName());
        return organizationRepository.saveOrganization(organization);
    }

    @Override
    public void deleteOrganization(Long id) {
        Organization organization = organizationRepository.findOrganizationById(id).orElseThrow();
        organizationRepository.deleteOrganization(organization);
    }

    public List<Organization> getAllOrganizationsByUser(Long id) {
        return organizationRepository.findAllOrganizationsByUser(id);
    }
}
