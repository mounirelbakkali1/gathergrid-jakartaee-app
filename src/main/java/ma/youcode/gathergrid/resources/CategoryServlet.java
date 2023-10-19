package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.gathergrid.domain.Category;
import ma.youcode.gathergrid.service.CategoryService;

import java.io.IOException;
@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {

    @Inject
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/categories.jsp");
        dispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        categoryService.createCategory(new Category(name , description));
       resp.sendRedirect(req.getContextPath()+"/dashboard");
    }
}