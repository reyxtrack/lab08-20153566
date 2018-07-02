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
            request.setAttribute("user",getUser(ID));
            request.setAttribute("login",getUser(uGoogle.getEmail().toString()));
            request.setAttribute("Roles", RolesControllerView.getAllRoles());
            

            //Ya que se quiere editar, el atributo permitirEdicion es verdadero. Este atributo se comprueba en el JSP.
            request.setAttribute("edit",true);
            request.setAttribute("action","edit");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                e.printStackTrace();
            }
        }
        else if (action.equals("view") && ID != null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Users/view.jsp");
            request.setAttribute("user",getUser(ID));
            request.setAttribute("login",getUser(uGoogle.getEmail().toString()));

            request.setAttribute("edit",false);
            request.setAttribute("action","View");
            try{
                dispatcher.forward(request,response);
            } catch (javax.servlet.ServletException e){
                e.printStackTrace();
            }

        }
        //Si no se encontró acción, regresa al inicio
        else {
            response.getWriter().println("<html><head><script>window.location.replace(\"../\");</script><body></body></html>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public static User getUser(String userID){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        try{
            User user = pm.getObjectById(User.class, userID);
            pm.close();
            return user;
        } catch (JDOObjectNotFoundException e){
           System.out.println("no se encuentra usuario");
           pm.close();
           return null;
        }
    }


    @SuppressWarnings("unchecked")
    static List<User> getAllUsers(){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        List<User> users = (List<User>) pm.newQuery("select from " + User.class.getName()).execute();
        pm.close();
        return users;
    }

   
    
}
