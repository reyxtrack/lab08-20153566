package controller.users;

import model.entity.Access;
import model.entity.Resource;
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

import controller.roles.RolesControllerView;

import java.io.IOException;
import java.util.List;

@SuppressWarnings("serial")
public class Metodos extends HttpServlet {
   
    public static User getUser(String userID){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        try{
            User user = pm.getObjectById(User.class, userID);
            pm.close();
            return user;
        } catch (JDOObjectNotFoundException e){
           System.out.println("no se encuentra usuario");
           pm.close();
           return null;
        }
    }


    @SuppressWarnings("unchecked")
    static List<User> getUsers(){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        List<User> users = (List<User>) pm.newQuery("select from " + User.class.getName()).execute();
        pm.close();
        return users;
    }
    @SuppressWarnings("unchecked")
    public static List<Role> getRoles(){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        List<Role> users = (List<Role>) pm.newQuery("select from " + Role.class.getName()).execute();
        pm.close();
        return users;
    }

    public static Role getRole(String key){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        Key k = KeyFactory.stringToKey(key);
        Role role = pm.getObjectById(Role.class, k);
        pm.close();
        return role;
    }
    @SuppressWarnings("unchecked")
    public static List<Resource> getResources(){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        List<Resource> users = (List<Resource>) pm.newQuery("select from " + Resource.class.getName()).execute();
        pm.close();
        return users;
    }

    public static Resource getResource(String key){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        Key k = KeyFactory.stringToKey(key);
        Resource resource = pm.getObjectById(Resource.class, k);
        pm.close();
        return resource;
    }
    @SuppressWarnings("unchecked")
    public static List<Access> getAccesss(){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        List<Access> access = (List<Access>) pm.newQuery("select from " + Access.class.getName()).execute();
        pm.close();
        return access;
    }

    public static Access getAccess(String key){
        PersistenceManager pm = controller.PMF.get().getPersistenceManager();
        Key k = KeyFactory.stringToKey(key);
        Access access = pm.getObjectById(Access.class, k);
        pm.close();
        return access;
    }
   
    
}
