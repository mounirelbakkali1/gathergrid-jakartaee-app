package ma.youcode.gathergrid.resources;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.gathergrid.domain.User;
import ma.youcode.gathergrid.service.UserService;

import java.io.IOException;


@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ConfirmPassword = request.getParameter("confirmPassword");
        User user = User.builder()
                .name(name)
                .email(email)
                .username(username)
                .password(password)
                .build();
        if(userService.validLoginDetails(user) && password.equals(ConfirmPassword)) {
            userService.registerUser(user);
            response.sendRedirect("login");
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.sendRedirect("register-error.html");
        }

    }
}
