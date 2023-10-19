package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.gathergrid.service.EventService;

import java.io.IOException;

@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"ADMIN"}))
@WebServlet(name = "CreateEventServlet",value = "/create-event")
public class EventServlet extends HttpServlet {
    @Inject
    private EventService eventService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        req.getRequestDispatcher("/WEB-INF/create-event.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

    }
}
