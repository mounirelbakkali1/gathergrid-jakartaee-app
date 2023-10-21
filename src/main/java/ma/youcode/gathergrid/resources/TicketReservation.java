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
import ma.youcode.gathergrid.domain.Ticket;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.dto.TicketDto;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.TicketService;
import ma.youcode.gathergrid.service.UserService;
import ma.youcode.gathergrid.utils.Response;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ticketReservation", urlPatterns = "/reservations")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"USER","ADMIN"}))
@Transactional
public class TicketReservation extends HttpServlet {
    private SecurityContext securityContext;
    private UserService userService;
    private TicketService ticketService;

    @Inject
    public TicketReservation(SecurityContext securityContext, UserService userService,TicketService ticketService) {
        this.securityContext = securityContext;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.findUserByUsername(securityContext.getCallerPrincipal().getName()).get();
        List<TicketDto> tickets = ticketService.findByUser(user.getUsername());
        req.setAttribute("reservations",tickets);
        req.setAttribute("user",user);
        req.getRequestDispatcher("/WEB-INF/views/reservations.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getQueryString();
        Long ticketId = Long.parseLong(path.split("=")[1]);
        Optional<Ticket> ticket = ticketService.findById(ticketId);
        if(ticket.isEmpty()){
            resp.sendError(404,"Ticket not found");
        }else{
            Response<TicketDto> response = ticketService.cancel(ticket.get());
        }
    }
}
