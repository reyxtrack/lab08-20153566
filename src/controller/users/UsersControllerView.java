/**
 * Esta clase procesa las peticiones de vista y edicion de un usuario
 */
package controller.users;

import model.entity.User;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserServiceFactory;

import controller.roles.RolesControllerView;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
public class UsersControllerView extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
        String action = request.getParameter("action");

        //Para evitar errores, si no hay ninguna accion, se establece a vacio.
        if (action == null)
            action = "";

        String ID = request.getParameter("ID");

        //Si se quiere cerrar la sesion actual
        if (action.equals("closeSession")){
            response.sendRedirect("/logout");
        }
        //Redirige al formulario para editar un usario (user/view)
        else if (action.equals("edit") && ID != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Users/view.jsp");
            request.setAttribute("user",Metodos.getUser(ID));
            request.setAttribute("login",Metodos.getUser(uGoogle.getEmail().toString()));
            request.setAttribute("Roles", Metodos.getRoles());
            

            //Ya que se quiere editar, el atributo permitirEdicion es verdadero. Este atributo se comprueba en el JSP.
            request.setAttribute("edit",true);
            request.setAttribute("action","Editar");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                e.printStackTrace();
            }
        }
        else if (action.equals("view") && ID != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Users/view.jsp");
            request.setAttribute("user",Metodos.getUser(ID));
            request.setAttribute("login",Metodos.getUser(uGoogle.getEmail().toString()));

            request.setAttribute("edit",false);
            request.setAttribute("action","Ver");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                e.printStackTrace();
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
