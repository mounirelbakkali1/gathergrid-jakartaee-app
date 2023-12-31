package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.domain.TicketType;
import ma.youcode.gathergrid.service.CategoryService;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.OrganizationService;
import ma.youcode.gathergrid.service.UserService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "dashboard", value = "/dashboard")
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"},value = ServletSecurity.EmptyRoleSemantic.PERMIT))
public class DashboardServlet extends HttpServlet {
    private Long userId;
    private EventService eventService;
    private OrganizationService organizationService;
    private SecurityContext securityContext;
    private UserService userService;
    private CategoryService categoryService;
    @Inject
    public DashboardServlet(EventService eventService, OrganizationService organizationService, SecurityContext securityContext, UserService userService, CategoryService categoryService) {
        this.eventService = eventService;
        this.organizationService = organizationService;
        this.securityContext = securityContext;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    public void init() throws ServletException {
        super.init();
        String userName = this.securityContext.getCallerPrincipal().getName();
        this.userId = userService.findUserByUsername(userName).get().getId();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("events",eventService.getAllEvents().getResult());
        req.setAttribute("ticketTypes",TicketType.values());
        req.setAttribute("categories",categoryService
                .getAllCategories()
                .getResult());
        List<Organization> allOrganizationsByUser = organizationService.getAllOrganizationsByUser(this.userId);
        req.setAttribute("organizations", allOrganizationsByUser);
        req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
    }
}
