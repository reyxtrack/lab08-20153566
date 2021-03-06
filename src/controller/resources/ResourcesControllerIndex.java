package controller.resources;

import controller.roles.RolesControllerView;
import controller.users.Metodos;
import controller.users.UsersControllerView;
import controller.PMF;
import model.entity.Access;
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

import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
public class ResourcesControllerIndex extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersistenceManager pm=PMF.get().getPersistenceManager();
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
    	int i;
		/**if(uGoogle.getEmail().equals("reyxtrack@gmail.com")){
			i=5;
			req.setAttribute("ERROR", i);
    try{
    	User user = Metodos.getUser(uGoogle.getEmail().toString());
        if (user == null) throw new NullPointerException("UsersControllerIndex: El usuario recibido es nulo.");
        
        req.setAttribute("user",user);
        String query1 = "select from " + Resource.class.getName();
		 	@SuppressWarnings("unchecked")
		List<Resource> resource = (List<Resource>) pm.newQuery(query1).execute();
		 
        req.setAttribute("resources", resource);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/index.jsp");
        dispatcher.forward(req,resp);
    

    }
    catch (Exception e){
    System.out.println("no tiene permmiso");
    	  e.printStackTrace();
        resp.sendRedirect("index.html"); }
		}
		**/
    /**	if(uGoogle==null){
			i=1;
			RequestDispatcher p= getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/index.jsp");
			req.setAttribute("user", Metodos.getUser(uGoogle.getEmail()));
			
			p.forward(req,  resp);
		}
		else{	
			String query= "select from "+ model.entity.User.class.getName()+" where email=='"+ uGoogle.getEmail()+"'"+
							" && status==true";
			List<model.entity.User> uSearch;
			try{
			 uSearch=(List<model.entity.User>) pm.newQuery(query).execute();	
			}
			catch(Exception e){
			 System.out.println("error:  "+ e);
				uSearch=null;	
			}
			
		if(uSearch.isEmpty()){
			i=2;
			RequestDispatcher p= getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/index.jsp");
			req.setAttribute("user", Metodos.getUser(uGoogle.getEmail()));
			
			req.setAttribute("ERROR",  i);
			p.forward(req,  resp);
		}
		else{
			System.out.println(req.getServletPath());
			String query2= "select from "+ model.entity.Resource.class.getName()+ " where Url=='"+ req.getServletPath()+"'"+" && status==true";
			@SuppressWarnings("unchecked")
			List<model.entity.Resource> rSearch=(List<model.entity.Resource>) pm.newQuery(query2).execute();
		if(rSearch.isEmpty()){
			i=3;
			RequestDispatcher p=getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/index.jsp");
			req.setAttribute("user", Metodos.getUser(uGoogle.getEmail()));
			
			req.setAttribute("ERROR", i);
			p.forward(req, resp);
		}
		else{
			
			String query3= "select from "+ Access.class.getName()+" where rol=='"+ uSearch.get(0).getRole() + "' && resource=='"+rSearch.get(0).getUrl()+"' && status==true";
			System.out.println(query3);		
			
			@SuppressWarnings("unchecked")
			List<model.entity.Access> aSearch= (List<model.entity.Access>) pm.newQuery(query3).execute();
			if(aSearch.isEmpty()){
				i=4;
				RequestDispatcher p= getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/index.jsp");
				req.setAttribute("user", Metodos.getUser(uGoogle.getEmail()));
				req.setAttribute("ERROR", i);
				p.forward(req, resp);
			}
			else{
			**/	i=5;
				req.setAttribute("ERROR", i);
        try{
        	User user = Metodos.getUser(uGoogle.getEmail().toString());
            if (user == null) throw new NullPointerException("UsersControllerIndex: El usuario recibido es nulo.");
            
            req.setAttribute("user",user);
            String query1 = "select from " + Resource.class.getName();
   		 	@SuppressWarnings("unchecked")
			List<Resource> resource = (List<Resource>) pm.newQuery(query1).execute();
   		 
            req.setAttribute("resources", resource);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Resources/index.jsp");
            dispatcher.forward(req,resp);
        

        }
        catch (Exception e){
        System.out.println("no tiene permmiso:"+ e);
        	  e.printStackTrace();
            resp.sendRedirect("index.html"); }
			
		}
    
		//}}}
	

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }
}
