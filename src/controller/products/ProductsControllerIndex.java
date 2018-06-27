package controller.products;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.jdo.PersistenceManager;
import javax.persistence.Access;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.server.spi.auth.common.User;
import com.google.appengine.api.users.UserServiceFactory;

import controller.PMF;
import model.entity.*;

@SuppressWarnings("serial")
public class ProductsControllerIndex extends HttpServlet {
	
	@SuppressWarnings("unchecked")
	public  void doGet(HttpServletRequest req, HttpServletResponse resp ) throws IOException, ServletException{
		com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
		int i;
		if(uGoogle==null){
			i=1;
			RequestDispatcher p= getServletContext().getRequestDispatcher("/WEB-INF/View/Errors/error1.jsp");
			req.setAttribute("ERROR",  i);
			p.forward(req,  resp);
		}
		else{
			PersistenceManager pm= PMF.get().getPersistenceManager();
			String query= "select from"+ User.class.getName()+" where email=='"+ uGoogle.getEmail()+"'" + " && status==true";
			List<User> uSearch=(List<User>) pm.newQuery(query).execute();
		if(uSearch.isEmpty()){
			i=2;
			RequestDispatcher p= getServletContext().getRequestDispatcher("/WEB-INF/View/Errors/error1.jsp");
			req.setAttribute("ERROR",  i);
			p.forward(req,  resp);
		}
		else{
			System.out.println(req.getServletPath());
			String query2= "select from "+ Resource.class.getName()+ " where name=='"+ req.getServletPath()+"'"+" && status==true";
			List<Resource> rSearch=(List<Resource>) pm.newQuery(query2).execute();
		if(rSearch.isEmpty()){
			i=3;
			RequestDispatcher p=getServletContext().getRequestDispatcher("/WEB-INF/View/Errors/error1.jsp");
			req.setAttribute("ERROR", i);
			p.forward(req, resp);
		}
		else{
			String query3= "select from "+ Access.class.getName()+" where roleID=="+ uSearch.get(0) + " && resourceID=="+rSearch.get(0)+" && status==true";
			List<Access> aSearch= (List<Access>) pm.newQuery(query3).execute();
			if(aSearch.isEmpty()){
				i=4;
				RequestDispatcher p=getServletContext().getRequestDispatcher("/WEB-INF/View/Errors/error1.jsp");
				req.setAttribute("ERROR", i);
				p.forward(req, resp);
			}
			else{
				RequestDispatcher p=getServletContext().getRequestDispatcher("/WEB-INF/View/Products/index.jsp");
				p.forward(req, resp);
			}
		}
		}
	}
	
	

}}
