package controller.roles;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.users.Metodos;
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

       
        if (action.equals("edit") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Roles/view.jsp");
            request.setAttribute("Role",Metodos.getRole(key));
            request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));

            request.setAttribute("edit",true);
            request.setAttribute("action","Editar");
            try{
              dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                System.err.println("error: " + e.getMessage());
            }
        }
        else if (action.equals("view") && key != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Roles/view.jsp");
            request.setAttribute("Role",Metodos.getRole(key));
            request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));

           request.setAttribute("edit",false);
            request.setAttribute("action","Ver");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                System.err.println("error: " + e.getMessage());
            }

        }
        else {
            response.sendRedirect("index.html");;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


   
}
