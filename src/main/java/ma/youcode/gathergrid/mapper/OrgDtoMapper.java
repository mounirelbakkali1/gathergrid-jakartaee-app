package ma.youcode.gathergrid.mapper;

import jakarta.enterprise.inject.Model;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.dto.OrganizationDto;

@Model
public class OrgDtoMapper {

    public OrganizationDto toDto(Organization organization){
        OrganizationDto organizationDto = new OrganizationDto();
        organizationDto.setName(organization.getName());
        organizationDto.setDescription(organization.getDescription());
        return organizationDto;
    }

    public Organization toEntity(OrganizationDto organizationDto){
        Organization organization = new Organization();
        organization.setName(organizationDto.getName());
        organization.setDescription(organizationDto.getDescription());
        return organization;
    }
}
