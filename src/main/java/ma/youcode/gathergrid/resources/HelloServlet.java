package ma.youcode.gathergrid.resources;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(name = "init_db", value = "/secure/init_db",loadOnStartup = 1)
public class HelloServlet extends HttpServlet {
  /*  @Override
    public void init() {
        try {
            super.init();
            EntityManagerFactory emf = jakarta.persistence.Persistence.createEntityManagerFactory("default");
            EntityManager entityManager = emf.createEntityManager();
            entityManager.close();
            System.out.println("Init DB");
        } catch (ServletException e) {
            System.out.println("Init DB error");
            e.printStackTrace();
        }

    }
    public void destroy() {
    }*/
}