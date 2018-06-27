package controller.resources;

import controller.roles.RolesControllerView;
import controller.users.UsersControllerView;
import informs.PMF;
import model.entity.Inform;
import model.entity.Resource;
import model.entity.User;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
public class ResourcesControllerIndex extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession sesion= request.getSession();

        try{
        	User user = UsersControllerView.getUser(sesion.getAttribute("userID").toString());
            if (user == null) throw new NullPointerException("UsersControllerIndex: El usuario recibido es nulo.");

            request.setAttribute("User",user);
            PersistenceManager pm = PMF.get().getPersistenceManager();
            String query = "select from " + Resource.class.getName();
   		 	List<Resource> resource = (List<Resource>) pm.newQuery(query).execute();
   		 
            request.setAttribute("ResourceList", resource);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/index.jsp");
            dispatcher.forward(request,response);
        

        }
        catch (Exception e){
        System.out.println("no tiene permmiso");
        	  e.printStackTrace();
            response.getWriter().println("<html><head><script>window.location.replace(\"../\")</script></head><body></body></html>");
        }
    }
    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
