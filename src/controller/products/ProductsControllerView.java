
package controller.products;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import controller.roles.RolesControllerView;
import controller.users.Metodos;
import controller.users.UsersControllerView;
import model.entity.Inform;
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
public class ProductsControllerView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
        PersistenceManager pm= PMF.get().getPersistenceManager();
    	String action = request.getParameter("action");

        if (action == null)
            action = "";

        String key = request.getParameter("key");
        Key k = KeyFactory.stringToKey(key);
        Inform inform = pm.getObjectById(Inform.class, k);
        pm.close();
        
        
       
        if (action.equals("edit") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Products/view.jsp");
            request.setAttribute("user",Metodos.getUser(uGoogle.getEmail().toString()));
            request.setAttribute("roles",Metodos.getRoles());
            request.setAttribute("inform",inform);
            request.setAttribute("edit",true);
            request.setAttribute("action","Editar");
            try{
                dispatcher.forward(request,response);
            } catch (Exception e){
                System.err.println("error: " + e.getMessage());
            }
        }
        else if (action.equals("view") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Products/view.jsp");
            request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));
            request.setAttribute("Resource",inform);
           
             request.setAttribute("edit",false);
            request.setAttribute("action","Ver");
            try{
                dispatcher.forward(request,response);
            } catch (Exception e){
            }

        }
        else {
            response.sendRedirect("index.html");
            }

        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    
    }
    
    
 
}
