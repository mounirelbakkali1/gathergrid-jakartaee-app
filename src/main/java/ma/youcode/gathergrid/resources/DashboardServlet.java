package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.gathergrid.service.EventService;

import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/dashboard")
public class DashboardServlet extends HttpServlet {
    @Inject
    private EventService eventService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(String.format("""
                        <html>
                        <body>
                        
                        <h3>Hello %s </h3>
                        
                        </body>
                        </html>
                        
                        ""","me"));
        //req.setAttribute("events",eventService.getAllEvents());
        //req.getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(req, resp);
    }
}
