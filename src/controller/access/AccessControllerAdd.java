package controller.access;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Key;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.resources.ResourcesControllerView;
import controller.roles.RolesControllerView;
import controller.users.UsersControllerView;
import model.entity.Access;
import model.entity.Resource;

import java.io.IOException;
import java.security.KeyFactory;

@SuppressWarnings("serial")
public class AccessControllerAdd extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();

             String request = req.getParameter("action");
      
            if(request.equals("create")){

                String rol = req.getParameter("rol");
                String resource= req.getParameter("resource");
                Boolean status = Boolean.parseBoolean(req.getParameter("status"));
                Access ac = new Access(rol, resource);
                try{
                    pm.makePersistent(ac);
                    System.out.println("ff");
                } finally {
                    System.out.println("Acceso creado");
                }

            }

            else if(request.equals("redirect")){
                HttpSession sesion= req.getSession();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/add.jsp");
                req.setAttribute("User",UsersControllerView.getUser(sesion.getAttribute("userID").toString()));
                req.setAttribute("Resources", ResourcesControllerView.getAllResources());
                req.setAttribute("Roles", RolesControllerView.getAllRoles());
                dispatcher.forward(req, resp);
            }

            else if( request.equals("update")){

                String a = (req.getParameter("key"));

                
                Resource resource1 = pm.getObjectById(Resource.class, a);

                resource1.setUrl(req.getParameter("url"));
                resource1.setStatus(Boolean.parseBoolean(req.getParameter("status")));
            

        }
            else{
            	   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/index.jsp");
                   dispatcher.forward(req, resp);
            }

        pm.close();
        try{
            resp.sendRedirect("/access");
        }
         catch (Exception e){
        }

        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
