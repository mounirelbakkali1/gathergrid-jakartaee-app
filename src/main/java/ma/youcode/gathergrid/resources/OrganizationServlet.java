package ma.youcode.gathergrid.resources;


import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "OrganizationServlet", value = "/organizations")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"USER","ADMIN"}))
public class OrganizationServlet {


}
