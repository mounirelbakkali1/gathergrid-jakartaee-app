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
import ma.youcode.gathergrid.domain.Category;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.domain.Organization;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.service.CategoryService;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.OrganizationService;
import ma.youcode.gathergrid.service.UserService;
import ma.youcode.gathergrid.utils.Response;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN","USER"}))
@WebServlet(name = "EventServlet",value = "/event")
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getUserPrincipal().getName();
        long userId = userService.findUserByUsername(userName).get().getId();
        List<Organization> allOrganizationsByUser = organizationService.getAllOrganizationsByUser(userId);
        if("edit".equals(req.getParameter("action"))){
            String eventId = req.getParameter("id");
            Optional<Event> optionalEvent = eventService.getEventById(Long.parseLong(eventId));
            optionalEvent.ifPresent(event -> req.setAttribute("event", event));
        }
        req.setAttribute("organizations",allOrganizationsByUser);
        req.setAttribute("categories",categoryService
                                            .getAllCategories()
                                            .getResult());
        req.getRequestDispatcher("/WEB-INF/event.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<Event> eventResponse = new Response<>();
        switch(req.getParameter("action")){
            case "post" -> {
                eventResponse = eventService.createEvent(eventBuilder(req));
                req.setAttribute("response",eventResponse);
                this.doGet(req,resp);
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
                this.doGet(req,resp);
            }
            case "delete" -> {
                Event event = eventBuilder(req);
                long id = Long.parseLong(req.getParameter("id"));
                event.setId(id);
                eventResponse = eventService.deleteEvent(event);
                req.setAttribute("response",eventResponse);
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
        int maxTickets = Integer.parseInt(req.getParameter("maxTickets"));
        return Event.builder()
                .name(name)
                .description(description)
                .date(date)
                .hour(time)
                .location(location)
                .category(category)
                .organization(organization)
                .numberOfTicketsAvailable(maxTickets)
                .build();
    }
}
