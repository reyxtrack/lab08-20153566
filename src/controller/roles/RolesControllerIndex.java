package controller.roles;

import controller.users.UsersControllerView;
import model.entity.User;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SuppressWarnings("serial")
public class RolesControllerIndex extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Se usa para revisar si hay una sesion activa
        HttpSession sesion= request.getSession();

        //Intenta hallar una sesion activa
        try{
            User user = UsersControllerView.getUser(sesion.getAttribute("userID").toString());
            if (user == null) throw new NullPointerException("UsersControllerIndex: El usuario recibido es nulo.");

            request.setAttribute("User",user);
            request.setAttribute("RoleList",RolesControllerView.getAllRoles());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Roles/index.jsp");
            dispatcher.forward(request,response);

        }
        //Si no la encuentra, redirige a la pagina inicial.
        catch (Exception e){
            e.printStackTrace();
            response.getWriter().println("<html><head><script>window.location.replace(\"../\")</script></head><body></body></html>");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
