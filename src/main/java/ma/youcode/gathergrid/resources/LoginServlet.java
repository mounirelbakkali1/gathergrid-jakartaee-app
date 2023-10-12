package ma.youcode.gathergrid.resources;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.service.UserService;

import java.io.IOException;


@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {


    private final  UserService userService = new UserService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = User.builder()
                .username(username)
                .password(password)
                .build();
        if (userService.isValidUser(user)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("hello");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("login-error.html");
        }
    }
}