package ma.youcode.gathergrid.service;

import com.oracle.wls.shaded.org.apache.xpath.operations.Or;
import ma.youcode.gathergrid.domain.Organization;

import java.util.List;
import java.util.Optional;

public interface IOrganizationService {
    boolean validOrganizationName(String name);

    Optional<Organization> findOrganizationByName(String name);

    List<Organization> getAllOrganizations();

    Organization saveOrganization(Organization organization);

    void deleteOrganization(Long id);
}
