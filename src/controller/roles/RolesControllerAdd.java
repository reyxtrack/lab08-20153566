package controller.roles;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import controller.users.UsersControllerView;
import model.entity.Role;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@SuppressWarnings("serial")
public class RolesControllerAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersistenceManager pm = controller.PMF.get().getPersistenceManager();

        //Accion a realizar
        String action = request.getParameter("action");

        if (action == null)
            action = "";

        switch (action){
            //Crea
            case "create":

                String name = request.getParameter("roleName");
                Boolean status = Boolean.parseBoolean(request.getParameter("roleStatus"));

                Role role = new Role(name,status);

                try{
                    pm.makePersistent(role);
                } finally {
                    System.out.println("Role creado");
                }

                break;

            case "redirect":
                HttpSession sesion= request.getSession();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Roles/Add.jsp");
                request.setAttribute("User",UsersControllerView.getUser(sesion.getAttribute("userID").toString()));
                dispatcher.forward(request, response);
                break;

            case "update":

                Key a = KeyFactory.stringToKey(request.getParameter("key"));

                Role role1 = pm.getObjectById(Role.class, a);

                role1.setName(request.getParameter("roleName"));
                role1.setStatus(Boolean.parseBoolean(request.getParameter("roleStatus")));
                //role1.setImgUrl(userImg);


                break;

        }

        pm.close();
        try{
            response.sendRedirect("/roles");
        }
        //Al redirigr al jsp para crear, se usa RequestDispatcher, y este entra en conflicto con sendRedirect.
        catch (IllegalStateException e){
            System.err.println("IllegalStateException: There was a double redirect.");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
