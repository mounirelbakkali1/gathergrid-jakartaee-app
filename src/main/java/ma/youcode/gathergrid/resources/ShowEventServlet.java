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
import ma.youcode.gathergrid.domain.TicketType;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.TicketService;
import ma.youcode.gathergrid.service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "showEvent", value = "/show-event")

public class ShowEventServlet extends HttpServlet {
    private User user;
    private Event currentEvent;
    private EventService eventService;
    private TicketService ticketService;
    private UserService userService;
    private SecurityContext securityContext;

    @Inject
    public ShowEventServlet(EventService eventService, TicketService ticketService, UserService userService, SecurityContext securityContext) {
        this.eventService = eventService;
        this.ticketService = ticketService;
        this.userService = userService;
        this.securityContext = securityContext;
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
            req.getRequestDispatcher("/WEB-INF/views/show-event.jsp").forward(req,resp);
        }


    }



}
