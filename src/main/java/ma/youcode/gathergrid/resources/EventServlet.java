package ma.youcode.gathergrid.resources;


import java.sql.Date;
import java.text.SimpleDateFormat;
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
import ma.youcode.gathergrid.service.CategoryService;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.service.UserService;
import ma.youcode.gathergrid.utils.Response;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN","USER"}))
@WebServlet(name = "EventServlet",value = "/event")
public class EventServlet extends HttpServlet {
    @Inject
    private UserService userService;
    @Inject
    private EventService eventService;
    @Inject
    private CategoryService categoryService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getUserPrincipal().getName();
        //User userId = userService.findUserByUsername(userName).get().getId();

        req.setAttribute("action",req.getParameter("action"));
        req.setAttribute("categories",categoryService
                                            .getAllCategories()
                                            .getResult());

        req.getRequestDispatcher("/WEB-INF/event.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<Event> eventResponse = new Response<>();
        Date eventDate = new Date(1970);
        try {
            eventDate = new Date(
                    new SimpleDateFormat("dd-MM-yyyy")
                    .parse(req.getParameter("date")
            ).getTime());
        } catch (Exception e) {
            eventResponse.setError(List.of(new Error("Date error")));
        }

        long millisecondsSinceMidnight = 12 * 60 * 60 * 1000; // 12:00:00 in milliseconds
        java.sql.Time time = new java.sql.Time(millisecondsSinceMidnight);

        Category category = categoryService.getCategoryById(
                Long.parseLong(req.getParameter("category"))
        ).getResult();

        eventResponse = eventService.createEvent(
                Event.builder()
                        .name(req.getParameter("name"))
                        .description(req.getParameter("description"))
                        .date(eventDate)
                        .hour(time)
                        .location(req.getParameter("location"))
                        .category(category)
                        .organization(
                                Organization.builder().id(Long.parseLong("1")).build()
                        )
                        .build()
        );
        req.setAttribute("response",eventResponse);
        req.getRequestDispatcher("/WEB-INF/event.jsp").forward(req,resp);
    }
}
