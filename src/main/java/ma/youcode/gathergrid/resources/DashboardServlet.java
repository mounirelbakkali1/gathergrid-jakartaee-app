package ma.youcode.gathergrid.resources;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.gathergrid.repositories.EventRepositoryImpl;
import ma.youcode.gathergrid.service.EventService;

import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/dashboard")
public class EventServlet extends HttpServlet {

    private EventService eventService = new EventService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //req.setAttribute("events", EventService.);

        // Forward the request to the JSP page
        //RequestDispatcher dispatcher = request.getRequestDispatcher("/yourpage.jsp").forward(request, response);
    }
}
