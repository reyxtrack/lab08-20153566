
package controller.access;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.resources.ResourcesControllerView;
import controller.roles.RolesControllerView;
import controller.users.Metodos;
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

          try{
        String key = request.getParameter("key");

        if (action.equals("edit") && key!= null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/view.jsp");
            request.setAttribute("access",Metodos.getAccess(key));
            request.setAttribute("user",Metodos.getUser(uGoogle.getEmail().toString()));
            request.setAttribute("Roles", Metodos.getRoles());
            request.setAttribute("Resources", Metodos.getResources());
            request.setAttribute("edit",true);
            request.setAttribute("action","Editar");
            
            try{
                dispatcher.forward(request,response);
            } catch (Exception e){
                System.out.println("Error " + e.getMessage());
            }
        }
        else if (action.equals("view") && key!= null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/view.jsp");
            request.setAttribute("access",Metodos.getAccess(key));
            request.setAttribute("user",Metodos.getUser(uGoogle.getEmail().toString()));

            request.setAttribute("edit",false);
            request.setAttribute("action","Ver");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
            System.out.println("error");     
            }

        }
       else {
            response.sendRedirect("/view");
        }
          }
          catch(Exception e){
        	  response.sendRedirect("/view");
          }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
    
 
  
}
