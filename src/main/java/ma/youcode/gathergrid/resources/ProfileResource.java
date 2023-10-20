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
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.OrganizationService;
import ma.youcode.gathergrid.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ServletSecurity(@HttpConstraint(rolesAllowed = {"USER","ADMIN"}))
@WebServlet("/profile")
public class ProfileResource extends HttpServlet {
        private SecurityContext securityContext;
        private UserService userService;
        private OrganizationService organizationService;
        private EventService eventService;
        private User user;
        @Inject
        public ProfileResource(SecurityContext securityContext, UserService userService, OrganizationService organizationService, EventService eventService) {
                this.securityContext = securityContext;
                this.userService = userService;
                this.organizationService = organizationService;
                this.eventService = eventService;
        }

        @Override
        public void init() throws ServletException {
                super.init();
                String name = securityContext.getCallerPrincipal().getName();
                Optional<User> user = userService.findUserByUsername(name);
                user.ifPresent(value -> this.user = value);
        }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                List<Organization> organizations = organizationService.getAllOrganizationsByUser(user.getId());
                List<Event> events = eventService.getAllEvents().getResult()
                                .stream()
                                        .filter(event -> event.getOrganization().getUser().getId().equals(user.getId()))
                                                .collect(Collectors.toList());
                req.setAttribute("organizations",organizations);
                req.setAttribute("events",events);
                req.setAttribute("user",user);
                req.getRequestDispatcher("/WEB-INF/profile.jsp").forward(req,resp);
        }
}
