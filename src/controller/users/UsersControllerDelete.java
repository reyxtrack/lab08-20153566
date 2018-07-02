package controller.users;

import model.entity.User;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;

@SuppressWarnings("serial")
public class UsersControllerDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();

        String ID = request.getParameter("ID");
        
        try{
            pm.deletePersistent(pm.getObjectById(User.class, ID));
        } catch (JDOObjectNotFoundException e){
            System.err.println("Exception catched -> " + e.getMessage());
        }

        response.sendRedirect("/users");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
