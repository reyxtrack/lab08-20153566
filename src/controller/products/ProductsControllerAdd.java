package controller.products;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.annotations.Key;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.users.UserServiceFactory;

import controller.roles.RolesControllerView;
import controller.users.Metodos;
import controller.users.UsersControllerView;
import model.entity.Inform;
import model.entity.Resource;
import model.entity.User;

import java.io.IOException;
import java.security.KeyFactory;

@SuppressWarnings("serial")
public class ProductsControllerAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
    	PersistenceManager pm = controller.PMF.get().getPersistenceManager();
    	User usuario=Metodos.getUser(uGoogle.getEmail());
        String action = request.getParameter("action");

        if (action.equals("create")){
                String name = request.getParameter("name");
                Boolean status = Boolean.parseBoolean(request.getParameter("status"));
                User user=Metodos.getUser(uGoogle.getEmail());
                
                Inform inform= new Inform(user.getName(),user.getRole(), user.getEmail(), request.getParameter("tipo"),request.getParameter("inform"));

                try{
                    pm.makePersistent(inform);
                } finally {
                    System.out.println("Producto creado");
                }

        }

                else if( action.equals("formulario")){
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Products/add.jsp");
                request.setAttribute("user",Metodos.getUser(uGoogle.getEmail().toString()));
                request.setAttribute("roles", Metodos.getRoles());                
                dispatcher.forward(request, response);
                
                
                }

                else if(action.equals("update")){

                String id = (request.getParameter("key"));

                
                Inform inform1 = pm.getObjectById(Inform.class, id);

                inform1.setInform(request.getParameter("inform"));
                inform1.setStatus(Boolean.parseBoolean(request.getParameter("status")));
                inform1.setType(request.getParameter("type"));
               
                }
                else {
                	RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/index.jsp");
                    request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));
                    dispatcher.forward(request, response);
                }
        	
        pm.close();
        try{
            response.sendRedirect("/resources");
        }
        catch (Exception e){
          System.out.println("null prro");
        }

        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}
