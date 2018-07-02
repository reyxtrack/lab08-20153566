package controller.resources;

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

import controller.users.Metodos;
import controller.users.UsersControllerView;
import model.entity.Resource;

import java.io.IOException;
import java.security.KeyFactory;

@SuppressWarnings("serial")
public class ResourcesControllerAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
    	PersistenceManager pm = controller.PMF.get().getPersistenceManager();

        String action = request.getParameter("action");

        if (action.equals("create")){
                String url = request.getParameter("name");
                Boolean status = Boolean.parseBoolean(request.getParameter("status"));

                Resource rec = new Resource(url,status);

                try{
                    pm.makePersistent(rec);
                } finally {
                    System.out.println("Recurso creado");
                }

        }

                else if( action.equals("formulario")){
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/add.jsp");
                request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));
                dispatcher.forward(request, response);
                }

                else if(action.equals("update")){

                String a = (request.getParameter("key"));

                
                Resource resource1 = pm.getObjectById(Resource.class, a);

                resource1.setUrl(request.getParameter("url"));
                resource1.setStatus(Boolean.parseBoolean(request.getParameter("status")));
               
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
