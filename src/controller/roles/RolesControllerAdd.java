package controller.roles;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import controller.users.Metodos;
import controller.users.UsersControllerView;
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
import java.io.IOException;
import java.util.List;


@SuppressWarnings("serial")
public class RolesControllerAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();

      try{
        String action = request.getParameter("action");

      
        switch (action){
            
            case "create":

                if(!duplicateRole(request.getParameter("roleName"))){
            	String name = request.getParameter("roleName");
                Boolean status = Boolean.parseBoolean(request.getParameter("roleStatus"));
                
                Role role = new Role(name,status);

                try{
                    pm.makePersistent(role);
                    
                } 
                catch(Exception e){
                System.out.println("errros: "+e);
                }
                	finally {
                
                    System.out.println("Role creado");
                }
                }
                break;

            case "formulario":
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Roles/add.jsp");
                request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));
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
      }
      catch(Exception e){
    	  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Roles/add.jsp");
          request.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));
          dispatcher.forward(request, response);
      }
        pm.close();
        try{
            response.sendRedirect("/roles");
        }
      
        catch (Exception e){
            System.out.println("error:"+e);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public static String crearRolDefault(){
    	model.entity.Role role;
    	PersistenceManager pm= controller.PMF.get().getPersistenceManager();
		
    	if(!duplicateRole("visitante")){    		    	  	
    		role=new Role("visitante", true); 
    		pm.makePersistent(role);
    		pm.close();
    	return role.getName();
    	}
    	return "visitante";
    }
    private static boolean duplicateRole(String name){
    	
    	PersistenceManager pm= controller.PMF.get().getPersistenceManager();		
    	try{
    	
    		List<Role> role=(List<Role>)pm.newQuery("select from "+Role.class.getName()+" where name=='"+name+"'").execute();
    		pm.close();
    		if(role.isEmpty())
    		return false;
    		else
    			return true;
    		
        } catch (Exception e ){
            System.out.println("errory :"+ e);
        	return false;
        }
       
    }    

}
