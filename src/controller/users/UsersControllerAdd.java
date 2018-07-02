package controller.users;

import model.entity.Role;
import model.entity.User;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.roles.RolesControllerAdd;
import controller.roles.RolesControllerView;

import java.io.IOException;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
    	PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        
        String action = request.getParameter("action");

        String email = request.getParameter("email");

        String name=request.getParameter("name");
        Role role=null;
        
       String ID = request.getParameter("id");
        switch (action) {
            
        case "login":

        	User useri;
        		if(!duplicateUser(request.getParameter("id"),pm)){
        			
                useri = new User(request.getParameter("id").toString(), request.getParameter("nick").toString().substring(0,request.getParameter("id").indexOf("@")), RolesControllerAdd.crearRolDefault());
                System.out.println("add       "+ID+" , "+name);
                
                try{
        			pm.makePersistent(useri);
        			pm.close();
        			System.out.println("hecho");
        				}
        		catch(Exception e){
        			System.out.println("error al persistir usuario");
        			
        		}
        		
        		
        		}
        		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/users");
                dispatcher.forward(request, response);
    	
                break;
        
            case "formulario":
                HttpSession sesion= request.getSession();
                RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/View/Users/Add.jsp");
                request.setAttribute("User",UsersControllerView.getUser(uGoogle.getEmail().toString()));
                request.setAttribute("Roles", RolesControllerView.getAllRoles());
                d.forward(request, response);
                break;

            case "create":
            	User user=new User(email, name, request.getParameter("role"));
                try{
            	pm.makePersistent(user);}
                catch(Exception e){
                	System.out.println("error "+e);
                }
            	break;

            case "update":

                User user1 = pm.getObjectById(User.class, ID);

                user1.setName(name);
                user1.setEmail(email);
               
                user1.setRole(request.getParameter("role"));

                break;

        }

        pm.close();
        try{
            response.sendRedirect("/users");
        }
        //Al redirigr al jsp para crear, se usa RequestDispatcher, y este entra en conflicto con sendRedirect.
        catch (IllegalStateException e){
            System.err.println("IllegalStateException: There was a double redirect.");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Redirige a doPost
        doPost(request, response);
    }
    //Revisa si un usuario existe: id -> ID del usuario (ejm. en richard@gmail.com el ID es richard)
    private boolean duplicateUser(String userID, PersistenceManager pm){
        try{
            //Intenta buscar en el DataStore un usuario con el ID respectivo.
            User usr = pm.getObjectById(User.class, userID);
            //Si lo encuentra devuelve true (el usuario si existe)
            return true;
        } catch (JDOObjectNotFoundException exc){
            //Si no lo encuentra, se lanza una Excepción, se captura, y se devuelve false (el usuario no existe)
            return false;
        }
    }

           


}
