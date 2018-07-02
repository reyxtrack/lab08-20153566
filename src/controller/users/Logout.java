package controller.users;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import model.entity.Role;

@SuppressWarnings("serial")
public class Logout extends HttpServlet {
 public void doGet(HttpServletRequest req, HttpServletResponse resp)
 throws IOException {
 
 	 UserService us = UserServiceFactory.getUserService();
	 User user = us.getCurrentUser();
	// us.createLogoutURL(req.getRequestURI());
	 //String action=req.getParameter("action");
	 //resp.sendRedirect("index.html");
	 if(user == null){
		 resp.sendRedirect(us.createLoginURL("/login"));
 	}else {
 		//RequestDispatcher d=getServletContext().getRequestDispatcher("/users/add");
 		//req.setAttribute("id", user.getEmail());
 		//req.setAttribute("nick", user.getNickname());
 		resp.sendRedirect(us.createLogoutURL("/login"));	
 	}
		/** user.getNickname();
		 user.getAuthDomain();
		 user.getEmail();
		 user.getUserId();
		 user.getFederatedIdentity();
		 us.isUserAdmin();
		 us.isUserLoggedIn();
		 us.getCurrentUser();
		 us.createLogoutURL(req.getRequestURI());
	**/
 	}
 }
