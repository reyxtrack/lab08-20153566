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

import controller.PMF;
import controller.roles.RolesControllerAdd;
import controller.roles.RolesControllerView;

import java.io.IOException;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
    	PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        try{
        String action = request.getParameter("action");
        									
        String email = request.getParameter("email");

        String name=request.getParameter("name");
        Role role=null;
        
       String ID = request.getParameter("id");
        switch (action) {
            
        case "login":

        	User useri;
        		if(!duplicateUser(request.getParameter("id"))){
        			
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
            
                RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/View/Users/add.jsp");
                request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));
                request.setAttribute("Roles", Metodos.getRoles());
                d.forward(request, response);
                break;

            case "create":
            	if(!duplicateUser(email)){User user=new User(email, name, request.getParameter("role"));
                try{
            	pm.makePersistent(user);}
                catch(Exception e){
                	System.out.println("error "+e);
                }}
            	
            	break;

            case "update":

                User user1 = pm.getObjectById(User.class, ID);

                user1.setName(name);
                user1.setEmail(email);
               
                user1.setRole(request.getParameter("role"));

                break;

        }
        }
        catch(Exception e){

            RequestDispatcher d = getServletContext().getRequestDispatcher("/WEB-INF/View/Users/add.jsp");
            request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));
            request.setAttribute("Roles", Metodos.getRoles());
            d.forward(request, response);
           
        }
        pm.close();
        try{
            response.sendRedirect("/users");
        }
       
        catch (IllegalStateException e){
            System.out.println("error:"+ e);
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Redirige a doPost
        doPost(request, response);
    }
    private boolean duplicateUser(String userID){
        try{
        	
        	PersistenceManager pm= PMF.get().getPersistenceManager();
           
        	User usr = pm.getObjectById(User.class, userID);
            return true;
        } catch (JDOObjectNotFoundException exc){
            return false;
        }
    }

           


}
