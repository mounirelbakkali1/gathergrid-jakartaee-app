package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.dto.TicketDto;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.TicketService;
import ma.youcode.gathergrid.service.UserService;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "reserveTicket", urlPatterns = "/reservations/new/*")
@NoArgsConstructor
@Transactional
public class TicketResource extends HttpServlet {

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
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        Long eventId = Long.parseLong(path.split("/")[1]);
        Optional<Event> eventById = eventService.getEventById(eventId);
        if(eventById.isEmpty()){
            resp.sendError(404,"Event not found");
        }else{
            req.setAttribute("event",eventById.get());
            req.getRequestDispatcher("/WEB-INF/views/reserveTicket.jsp").forward(req,resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("eventName");
        String ticketType = req.getParameter("ticketType");
        String quantity = req.getParameter("quantity");
        Optional<User> userOptional = userService.findUserByUsername(securityContext.getCallerPrincipal().getName());
        if (userOptional.isEmpty()){
            resp.sendError(404,"User not found");
        }
        User user = userOptional.get();
        TicketDto ticketDto = TicketDto.builder()
                .username(user.getUsername())
                .eventName(eventName)
                .quantity(Integer.parseInt(quantity))
                .ticketType(ticketType)
                .build();
        ticketService.save(ticketDto);
        resp.sendRedirect("/events");


    }
}
