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
import ma.youcode.gathergrid.service.CategoryService;
import ma.youcode.gathergrid.service.EventService;
import ma.youcode.gathergrid.utils.Response;

import java.io.IOException;
import java.util.List;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN","USER"}))
@WebServlet(name = "EventServlet",value = "/event")
public class EventServlet extends HttpServlet {
    @Inject
    private EventService eventService;
    @Inject
    private CategoryService categoryService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action",req.getParameter("action"));
        req.setAttribute("categories",categoryService
                                            .getAllCategories()
                                            .getResult());
        req.getRequestDispatcher("/WEB-INF/event.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Response<Event> eventResponse = new Response<>();
        try {
            Date eventDate = new Date(
                    new SimpleDateFormat("dd-MM-yyyy")
                    .parse(req.getParameter("date")
            ).getTime());

            long millisecondsSinceMidnight = 12 * 60 * 60 * 1000; // 12:00:00 in milliseconds
            java.sql.Time time = new java.sql.Time(millisecondsSinceMidnight);

            eventResponse = eventService.createEvent(
                    new Event(
                            //null,
                            //req.getParameter("name"),
                            //eventDate,
                            //time,
                            //req.getParameter("location"),
                            //req.getParameter("description")
                    )
            );
        } catch (Exception e) {
             eventResponse.setError(List.of(new Error("Date error")));
        }
        req.setAttribute("result",eventResponse);
        req.getRequestDispatcher("/WEB-INF/event.jsp").forward(req,resp);
    }
}
