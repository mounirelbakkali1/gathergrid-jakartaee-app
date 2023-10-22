package ma.youcode.gathergrid.resources;

import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
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
    @Inject
    private SecurityContext securityContext ;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(String.format("trying to login with %s %s",username,password));
        Credential credential = new UsernamePasswordCredential(username,new Password(password));
        AuthenticationStatus status = securityContext.authenticate(request,response, AuthenticationParameters.withParams().credential(credential));
        System.out.println("------------"+status);
        if(status.equals(AuthenticationStatus.SEND_FAILURE)){
            response.sendRedirect("login?error=failed");
        }else if(status.equals(AuthenticationStatus.SUCCESS)){
            response.sendRedirect(request.getContextPath()+"/dashboard");
        }
    }
}