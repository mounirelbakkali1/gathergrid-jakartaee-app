package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.dto.OrganizationDto;
import ma.youcode.gathergrid.mapper.OrgDtoMapper;
import ma.youcode.gathergrid.service.IOrganizationService;
import ma.youcode.gathergrid.service.OrganizationService;
import ma.youcode.gathergrid.utils.Response;

import java.util.Optional;

@Path("organizations")
@NoArgsConstructor
@Transactional
public class OrganizationResource {

    private IOrganizationService organizationService;
    private OrgDtoMapper orgDtoMapper;

    @Inject
    public OrganizationResource(OrganizationService organizationService, OrgDtoMapper orgDtoMapper) {
        this.organizationService = organizationService;
        this.orgDtoMapper = orgDtoMapper;
    }


    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response<OrganizationDto> getOrganizationByName(@PathParam("name") String name){
        Response<OrganizationDto> response = new Response<>();
        Optional<Organization> organizationByName = organizationService.findOrganizationByName(name.toLowerCase());
        organizationByName.ifPresent(organization -> response.setResult(orgDtoMapper.toDto(organization)));
        return response;
    }
    @DELETE
    @Path("/{id}")
    public void deleteOrganization(@PathParam("id") Long id){
        organizationService.deleteOrganization(id);
    }
}
