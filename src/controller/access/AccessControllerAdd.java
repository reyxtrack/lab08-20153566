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

import com.google.appengine.api.users.UserServiceFactory;

import controller.resources.ResourcesControllerView;
import controller.roles.RolesControllerView;
import controller.users.Metodos;
import controller.users.UsersControllerView;
import model.entity.Access;
import model.entity.Resource;
import model.entity.Role;

import java.io.IOException;
import java.security.KeyFactory;
import java.util.List;

@SuppressWarnings("serial")
public class AccessControllerAdd extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();

             try{
            	 String rq = req.getParameter("action");
             
             
            
            if(rq.equals("create")){
            	String rol = req.getParameter("rol");
                String resource= req.getParameter("resource");
            	System.out.println("1");
                if(!duplicateAccess(rol,resource)){
            		Boolean status = Boolean.parseBoolean(req.getParameter("status"));
            		Access ac = new Access(rol, resource,status);
            		System.out.println("2");
            		try{
                    pm.makePersistent(ac);
                    System.out.println("ff");
            		}
            		finally {
                    System.out.println("Acceso creado");
            		}
                }
            }

            else if(rq.equals("formulario")){
               
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/add.jsp");
                req.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));
                req.setAttribute("Resources", Metodos.getResources());
                req.setAttribute("Roles", Metodos.getRoles());
                dispatcher.forward(req, resp);
            }

            else if( rq.equals("update")){

                String a = (req.getParameter("key"));

                
                Access acceso = pm.getObjectById(Access.class, a);

                acceso.setResource(req.getParameter("resource"));
                acceso.setStatus(Boolean.parseBoolean(req.getParameter("status")));
                acceso.setRol(req.getParameter("rol"));

        }
            else{
            	   RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/index.jsp");
                   dispatcher.forward(req, resp);
            }
           }
             catch(Exception e){
            	 System.out.println("error:" +e);
            	 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Access/add.jsp");
                 req.setAttribute("User",Metodos.getUser(uGoogle.getEmail().toString()));
                 req.setAttribute("Resources", Metodos.getResources());
                 req.setAttribute("Roles", Metodos.getRoles());
                 dispatcher.forward(req, resp);
             }
        pm.close();
        try{
            resp.sendRedirect("/access");
            
        }
         catch (Exception e){
        	 System.out.println("ERROR: "+e);
        }

        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
    private boolean duplicateAccess(String role, String resource){
    	
    	PersistenceManager pm= controller.PMF.get().getPersistenceManager();		
    	try{
    	
    		List<Access> access=(List<Access>)pm.newQuery("select from "+Access.class.getName()+" where rol=='"+role+"' && resource=='"+resource+"'").execute();
    		pm.close();
    		if(access.isEmpty())
    			return false;
    		else 
    			return true;
    		   	   
        } catch (Exception e ){
            System.out.println("errory :"+ e);
        	return false;
        }
       
    }    

}
