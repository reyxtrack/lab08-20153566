package controller.products;

import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.server.spi.auth.common.User;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import controller.users.Metodos;
import controller.users.UsersControllerView;
import model.entity.Inform;
import model.entity.*;

@SuppressWarnings("serial")
public class ProductsControllerIndex extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public  void doPost(HttpServletRequest req, HttpServletResponse resp ) throws IOException, ServletException{
		com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
		int i;
		if(uGoogle==null){
			i=1;
			RequestDispatcher p= getServletContext().getRequestDispatcher("/WEB-INF/View/Products/index.jsp");
			req.setAttribute("user", Metodos.getUser(uGoogle.getEmail()));
			
			p.forward(req,  resp);
		}
		else{	
			PersistenceManager pm= PMF.get().getPersistenceManager();
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
			RequestDispatcher p= getServletContext().getRequestDispatcher("/WEB-INF/View/Products/index.jsp");
			req.setAttribute("user", Metodos.getUser(uGoogle.getEmail()));
			
			req.setAttribute("ERROR",  i);
			p.forward(req,  resp);
		}
		else{
			System.out.println(req.getServletPath());
			String query2= "select from "+ model.entity.Resource.class.getName()+ " where Url=='"+ req.getServletPath()+"'"+" && status==true";
			List<model.entity.Resource> rSearch=(List<model.entity.Resource>) pm.newQuery(query2).execute();
		if(rSearch.isEmpty()){
			i=3;
			RequestDispatcher p=getServletContext().getRequestDispatcher("/WEB-INF/View/Errors/error.jsp");
			req.setAttribute("user", Metodos.getUser(uGoogle.getEmail()));
			
			req.setAttribute("ERROR", i);
			p.forward(req, resp);
		}
		else{
			
			String query3= "select from "+ Access.class.getName()+" where rol=='"+ uSearch.get(0).getRole() + "' && resource=='"+rSearch.get(0).getUrl()+"' && status==true";
			System.out.println(query3);		
			
			List<model.entity.Access> aSearch= (List<model.entity.Access>) pm.newQuery(query3).execute();
			if(aSearch.isEmpty()){
				i=4;
				RequestDispatcher p= getServletContext().getRequestDispatcher("/WEB-INF/View/Products/index.jsp");
				req.setAttribute("user", Metodos.getUser(uGoogle.getEmail()));
				req.setAttribute("ERROR", i);
				p.forward(req, resp);
			}
			else{
				i=5;
				req.setAttribute("ERROR", i);
				RequestDispatcher p=getServletContext().getRequestDispatcher("/WEB-INF/View/Products/index.jsp");
				PersistenceManager pm1 = PMF.get().getPersistenceManager();
				 String query1 = "select from " + Inform.class.getName();
				 List<model.entity.Inform> informs = (List<model.entity.Inform>)pm1.newQuery(query1).execute();
				  model.entity.User usuario = Metodos.getUser(uGoogle.getEmail().toString());
		            if (usuario == null) throw new NullPointerException("No existe ese usuario");
		            i=0;
		           req.setAttribute("ERROR", i); 		;
		            req.setAttribute("user", usuario);   
				 req.setAttribute("informs", informs);
				p.forward(req, resp);
			
			}
		
		}}}}
@SuppressWarnings("unchecked")
public  void doGet(HttpServletRequest req, HttpServletResponse resp ) throws IOException, ServletException{
	doPost(req,resp);
}
}
	


