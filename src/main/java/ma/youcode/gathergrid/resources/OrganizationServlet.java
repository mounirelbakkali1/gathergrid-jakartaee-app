package ma.youcode.gathergrid.resources;


import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.service.OrganizationService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrganizationServlet", value = "/organizations")
@ServletSecurity(httpMethodConstraints = {
        @HttpMethodConstraint(value = "GET",rolesAllowed = {"USER","ADMIN"}),
        @HttpMethodConstraint(value = "POST",rolesAllowed = {"ADMIN"})
})
public class OrganizationServlet extends HttpServlet {

    private final OrganizationService organizationService;

    @Inject
    public OrganizationServlet(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Organization> organizations = organizationService.getAllOrganizations();
        req.setAttribute("organizations",organizations);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
