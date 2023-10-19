package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.NoArgsConstructor;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.dto.OrganizationDto;
import ma.youcode.gathergrid.mapper.OrgDtoMapper;
import ma.youcode.gathergrid.service.OrganizationService;
import ma.youcode.gathergrid.utils.Response;

import java.util.Optional;

@Path("organizations")
@NoArgsConstructor
public class OrganizationResource {

    private OrganizationService organizationService;
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
        Optional<Organization> organizationByName = organizationService.findOrganizationByName(name);
        organizationByName.ifPresent(organization -> response.setResult(orgDtoMapper.toDto(organization)));
        return response;
    }
    @DELETE
    @Path("/{id}")
    public void deleteOrganization(@PathParam("id") Long id){
        organizationService.deleteOrganization(id);
    }
}
