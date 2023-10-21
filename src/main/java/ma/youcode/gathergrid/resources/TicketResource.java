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
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.Ticket;
import ma.youcode.gathergrid.domain.TicketType;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.dto.TicketDto;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.TicketService;
import ma.youcode.gathergrid.service.UserService;
import ma.youcode.gathergrid.utils.Response;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "reserveTicket", value = "/new-reservation")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"USER","ADMIN"}))
@Transactional
public class TicketResource extends HttpServlet {
    private User user;
    private Event currentEvent;
    private EventService eventService;
    private TicketService ticketService;
    private UserService userService;
    private SecurityContext securityContext;
    @Inject
    public TicketResource(EventService eventService, TicketService ticketService, UserService userService, SecurityContext securityContext) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
        this.securityContext = securityContext;
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
        String path = req.getQueryString();
        Long eventId = Long.parseLong(path.split("=")[1]);
        Optional<Event> eventById = eventService.getEventById(eventId);
        if(eventById.isEmpty()){
            resp.sendError(404,"Event not found");
        }else{
            currentEvent = eventById.get();
            req.setAttribute("event",currentEvent);
            // ticket type array from enum
            req.setAttribute("ticketTypes", List.of(TicketType.values()));
            req.getRequestDispatcher("/WEB-INF/views/reserveTicket.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ticketType = req.getParameter("ticketType");
        String quantity = req.getParameter("quantity");
        User user = userService.findUserByUsername(securityContext.getCallerPrincipal().getName()).get();
        Ticket ticket = Ticket.builder()
                .user(user)
                .event(currentEvent)
                .quantity(Integer.parseInt(quantity))
                .ticketType(TicketType.valueOf(ticketType))
                .reservationDate(new Date(System.currentTimeMillis()))
                .build();
        Response<TicketDto> response = ticketService.save(ticket);
        List<Error> errorList = response.getError();
        if(errorList!=null && !errorList.isEmpty()){
            req.setAttribute("errors", errorList);
            req.getRequestDispatcher("/WEB-INF/views/reserveTicket.jsp").forward(req,resp);
        }
        resp.sendRedirect(req.getContextPath()+"/profile");

    }
}
