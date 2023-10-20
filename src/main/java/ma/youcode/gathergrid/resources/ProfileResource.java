package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.dto.TicketDto;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.OrganizationService;
import ma.youcode.gathergrid.service.TicketService;
import ma.youcode.gathergrid.service.UserService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ServletSecurity(@HttpConstraint(rolesAllowed = {"USER","ADMIN"}))
@WebServlet(value = "/profile",name = "profile")
@Transactional
public class ProfileResource extends HttpServlet {
        private SecurityContext securityContext;
        private UserService userService;
        private OrganizationService organizationService;
        private EventService eventService;
        private TicketService ticketService;
        private User user;
        @Inject
        public ProfileResource(SecurityContext securityContext, UserService userService, OrganizationService organizationService, EventService eventService, TicketService ticketService) {
                this.securityContext = securityContext;
                this.userService = userService;
                this.organizationService = organizationService;
                this.eventService = eventService;
                this.ticketService = ticketService;
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
                List<TicketDto> tickets = ticketService.findByUser(user.getUsername());
                req.setAttribute("organizations",organizations);
                req.setAttribute("tickets",tickets);
                req.setAttribute("events",events);
                req.setAttribute("user",user);
                req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req,resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                String ConfirmPassword = req.getParameter("confirmPassword");
                if(!password.isEmpty() && !password.equals(ConfirmPassword)){
                        req.setAttribute("error","Passwords don't match");
                } else if (password.isEmpty()) {
                        password = user.getPassword();
                }
                User updatedUser = User.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .role(user.getRole())
                        .email(email)
                        .name(name)
                        .password(password)
                        .build();
                userService.updateUserInfo(updatedUser);
                resp.sendRedirect(req.getContextPath() + "/profile");
        }
}
