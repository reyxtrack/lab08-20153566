package controller.roles;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import model.entity.Role;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("serial")
public class RolesControllerDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersistenceManager pm = controller.PMF.get().getPersistenceManager();

        try {
            Key k = KeyFactory.stringToKey(request.getParameter("key"));
            try{
                pm.deletePersistent(pm.getObjectById(Role.class, k));
            } catch (JDOObjectNotFoundException e){
                System.err.println("Exception catched -> " + e.getMessage());
            }


        } catch (NullPointerException e){
            System.err.println("Exception captured -> " + e.getMessage());
        }

        response.sendRedirect("/roles");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
