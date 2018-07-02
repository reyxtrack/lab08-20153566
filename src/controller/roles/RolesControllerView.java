package controller.roles;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.users.UsersControllerView;
import model.entity.Role;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



@SuppressWarnings("serial")
public class RolesControllerView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
        String action = request.getParameter("action");

        //Para evitar errores, si no hay ninguna accion, se establece a vacio.
        if (action == null)
            action = "";

        String key = request.getParameter("key");

        //Redirige al formulario para editar un Role (role/view)
        if (action.equals("edit") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Roles/view.jsp");
            request.setAttribute("Role",getRole(key));
            request.setAttribute("User",UsersControllerView.getUser(uGoogle.getEmail().toString()));

            request.setAttribute("edit",true);
            request.setAttribute("action","edit");
            try{
              dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                System.err.println("error: " + e.getMessage());
            }
        }
        //Redirige al formulario para ver un usuario (user/view)
        else if (action.equals("view") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Roles/view.jsp");
            request.setAttribute("Role",getRole(key));
            request.setAttribute("User",UsersControllerView.getUser(uGoogle.getEmail().toString()));

           request.setAttribute("edit",false);
            request.setAttribute("action","View");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                System.err.println("error: " + e.getMessage());
            }

        }
        else {
            response.getWriter().println("<html><head><script>window.location.replace(\"../\");</script><body></body></html>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


    @SuppressWarnings("unchecked")
    public static List<Role> getAllRoles(){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        List<Role> users = (List<Role>) pm.newQuery("select from " + Role.class.getName()).execute();
        pm.close();
        return users;
    }

    public static Role getRole(String key){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        Key k = KeyFactory.stringToKey(key);
        Role role = pm.getObjectById(Role.class, k);
        pm.close();
        return role;
    }
}
