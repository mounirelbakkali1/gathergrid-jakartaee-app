package ma.youcode.gathergrid.resources;

import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login",
                errorPage = "/login?error=failed"
        )
)
@ServletSecurity(@HttpConstraint(rolesAllowed = {"USER","ADMIN"}))
@WebServlet("/secure")
public class SecureResource extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String username = null ;
                Optional<Principal> userPrincipal = Optional.ofNullable(req.getUserPrincipal());
                if(userPrincipal.isPresent() && userPrincipal.get().getName() !=null){
                        username = userPrincipal.get().getName();
                }
                resp.getWriter().write(String.format("""
                        <html>
                        <body>
                        
                        <h3>Hello %s </h3>
                        
                        </body>
                        </html>
                        
                        """,username));
        }
}
