
package controller.access;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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
        
        String req = request.getParameter("action");

                if (req == null)
            req = "";

        String key = request.getParameter("key");

        if (req.equals("editRedirect") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/view.jsp");
            request.setAttribute("Access",AccessControllerView.getAccess(key));
            request.setAttribute("User",UsersControllerView.getUser(request.getSession().getAttribute("userID").toString()));

            request.setAttribute("edit",true);
            request.setAttribute("action","Edit");
            
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                System.err.println("Exception captured -> " + e.getMessage());
            }
        }
        //Redirige al formulario para ver un usuario (user/view)
        else if (req.equals("viewRedirect") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/view.jsp");
            request.setAttribute("Access",AccessControllerView.getAccess(key));
            request.setAttribute("UserLogged",UsersControllerView.getUser(request.getSession().getAttribute("userID").toString()));

            request.setAttribute("editAllowed",false);
            request.setAttribute("action","View");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                 }

        }
       else {
            response.getWriter().println("<html><head><script>window.location.replace(\"../\");</script><body></body></html>");
        }

        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
