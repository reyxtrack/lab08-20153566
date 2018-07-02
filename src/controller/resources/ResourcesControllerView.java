
package controller.resources;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.roles.RolesControllerView;
import controller.users.Metodos;
import controller.users.UsersControllerView;
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
public class ResourcesControllerView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
        String action = request.getParameter("action");

        if (action == null)
            action = "";

        String key = request.getParameter("key");

       
        if (action.equals("edit") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/view.jsp");
            request.setAttribute("Resource",Metodos.getResource(key));
            request.setAttribute("user",Metodos.getUser(uGoogle.getEmail().toString()));


            request.setAttribute("edit",true);
            request.setAttribute("action","Edit");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                System.err.println("Exception captured -> " + e.getMessage());
            }
        }
        //Redirige al formulario para ver un usuario (user/view)
        else if (action.equals("view") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/view.jsp");
            request.setAttribute("Resource",Metodos.getResource(key));
            request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));

             request.setAttribute("edit",false);
            request.setAttribute("action","View");
            try{
                dispatcher.forward(request,response);
            } catch (Exception e){
            }

        }
        //Si no se encontró acción, regresa al inicio
        else {
            response.getWriter().println("<html><head><script>window.location.replace(\"../\");</script><body></body></html>");
        }

        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    
    }
    
    
    
}
