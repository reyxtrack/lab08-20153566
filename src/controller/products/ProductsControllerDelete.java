package controller.products;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import model.entity.Access;
import model.entity.Inform;
import model.entity.Resource;
import model.entity.Role;

import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings("serial")
public class ProductsControllerDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        try {
            Key k = KeyFactory.stringToKey(request.getParameter("key"));
            try{
                pm.deletePersistent(pm.getObjectById(Inform.class, k));
            } catch (Exception e){
                System.err.println("error" + e.getMessage());
            }


        } catch (Exception e){
            System.err.println("error: " + e.getMessage());
        }



        response.sendRedirect("/products");
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request,response);
    }
}
