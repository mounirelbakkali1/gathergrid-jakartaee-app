package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.gathergrid.domain.Event;
import ma.youcode.gathergrid.service.EventService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventResource", value = "/events")
public class EventResource extends HttpServlet {
    private List<Event> activeEvents;

    private EventService eventService ;
    @Inject
    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("events",activeEvents);
        req.getRequestDispatcher("/WEB-INF/views/events.jsp").forward(req,resp);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        activeEvents = eventService.getAllEvents().getResult();
    }
}
