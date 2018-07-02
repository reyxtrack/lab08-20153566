
package controller.access;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.resources.ResourcesControllerView;
import controller.roles.RolesControllerView;
import controller.users.UsersControllerView;
import model.entity.Access;
import model.entity.Resource;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
public class AccessControllerView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
        String action = request.getParameter("action");

          if (action==null)
            action = "";

        String key = request.getParameter("key");

        if (action.equals("edit") && key!= null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/view.jsp");
            request.setAttribute("access",AccessControllerView.getAccess(key));
            request.setAttribute("user",UsersControllerView.getUser(uGoogle.getEmail().toString()));
            request.setAttribute("Roles", RolesControllerView.getAllRoles());
            request.setAttribute("Resources", ResourcesControllerView.getAllResources());
            request.setAttribute("edit",true);
            request.setAttribute("action","edit");
            
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                System.err.println("Exception captured -> " + e.getMessage());
            }
        }
        else if (action.equals("view") && key!= null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/view.jsp");
            request.setAttribute("access",AccessControllerView.getAccess(key));
            request.setAttribute("user",UsersControllerView.getUser(uGoogle.getEmail().toString()));

            request.setAttribute("edit",false);
            request.setAttribute("action","view");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
            System.out.println("error");     
            }

        }
       else {
            response.getWriter().println("<html><head><script>window.location.replace(\"../\");</script><body></body></html>");
        }

        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
    
 
    @SuppressWarnings("unchecked")
    public static List<Access> getAllAccess(){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        List<Access> access = (List<Access>) pm.newQuery("select from " + Access.class.getName()).execute();
        pm.close();
        return access;
    }

    public static Access getAccess(String key){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        Key k = KeyFactory.stringToKey(key);
        Access access = pm.getObjectById(Access.class, k);
        pm.close();
        return access;
    }
}
