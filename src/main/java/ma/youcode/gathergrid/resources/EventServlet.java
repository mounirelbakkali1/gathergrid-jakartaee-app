package ma.youcode.gathergrid.resources;


import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import ma.youcode.gathergrid.domain.*;
import ma.youcode.gathergrid.service.CategoryService;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.OrganizationService;
import ma.youcode.gathergrid.service.UserService;
import ma.youcode.gathergrid.utils.Response;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN","USER"}))
@WebServlet(name = "EventServlet",value = "/event")
@Transactional
public class EventServlet extends HttpServlet {
    @Inject
    private UserService userService;
    @Inject
    private EventService eventService;
    @Inject
    private CategoryService categoryService;
    @Inject
    private OrganizationService organizationService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<Event> eventResponse = new Response<>();
        switch(req.getParameter("action")){
            case "post" -> {
                Event event = eventBuilder(req);
                List<TicketPack> ticketPacks = new ArrayList<>();
                int numberOfTicketsAvailable = 0;
                for(TicketType type :TicketType.values()){
                    String stringType = type.toString();
                    String ticketType = req.getParameter(stringType.toLowerCase() + "-ticket-price" );
                    String ticketAvailablePlaces = req.getParameter(stringType.toLowerCase() + "-available-places" );
                    if( ticketType != null || ticketAvailablePlaces != null){
                        float ticketPrice = Float.parseFloat(
                                req.getParameter(stringType.toLowerCase() + "-ticket-price")
                        );
                        int number = Integer.parseInt(ticketAvailablePlaces);
                        numberOfTicketsAvailable += number;
                        ticketPacks.add(
                                TicketPack.builder()
                                    .ticketType(type)
                                    .price(ticketPrice)
                                        .quantity(number)
                                    .build()
                        );
                    }
                }
                event.setNumberOfTicketsAvailable(numberOfTicketsAvailable);
                event.setTicketPacks(ticketPacks);

                eventResponse = eventService.createEvent(event);
                req.setAttribute("response",eventResponse);
                resp.sendRedirect(req.getContextPath()+ "/dashboard");
                //this.doGet(req,resp);
            }
            case "edit" -> {
                this.doGet(req,resp);
            }
            case "put" -> {
                Event event = eventBuilder(req);
                long id = Long.parseLong(req.getParameter("id"));
                event.setId(id);
                eventResponse = eventService.updateEvent(event);
                req.setAttribute("response",eventResponse);
                resp.sendRedirect(req.getContextPath()+ "/dashboard");
                this.doGet(req,resp);
            }
            case "delete" -> {
                long id = Long.parseLong(req.getParameter("id"));
                eventResponse = eventService.deleteEvent(id);
                req.setAttribute("response",eventResponse);
                resp.sendRedirect(req.getContextPath()+ "/dashboard");
                this.doGet(req,resp);
            }
        }
    }


    private Event eventBuilder(HttpServletRequest req){
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Date date = Date.valueOf(req.getParameter("date"));
        Time time = Time.valueOf(req.getParameter("time") + ":00");
        String location = req.getParameter("location");
        Category category = Category.builder().id(
                Long.parseLong(req.getParameter("category"))
        ).build();
        Organization organization = Organization.builder().id(
                Long.parseLong(req.getParameter("organization"))
        ).build();
        //int maxTickets = Integer.parseInt(req.getParameter("maxTickets"));
        return Event.builder()
                .name(name)
                .description(description)
                .date(date)
                .hour(time)
                .location(location)
                .category(category)
                .organization(organization)
                //.numberOfTicketsAvailable(maxTickets)
                .build();
    }
}
