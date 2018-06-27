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

import controller.roles.RolesControllerView;

import java.io.IOException;

@SuppressWarnings("serial")
public class UsersControllerAdd extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PersistenceManager pm = controller.PMF.get().getPersistenceManager();

        //Accion a realizar
        String action = request.getParameter("action");



        //Email del usuario
        String userEmail = request.getParameter("userEmail");

        //Solo se usa al actualizar un usuario.
        String prevUserID = request.getParameter("userID");

        //El ID del usuario. Este id se obtiene del email -> en richard@gmail.com el ID es richard
        String userID;
        try {
            userID = userEmail.substring(0,userEmail.indexOf("@"));
        } catch (NullPointerException e){
            userID = prevUserID;
        }

       String userName = request.getParameter("userName");
        String userImg = request.getParameter("userImg");
        String userRole = request.getParameter("rol");
        switch (action) {
            
        case "logIn":

                HttpSession misesion = request.getSession();

                crearUsuario(userID, userEmail, userName, userImg, userRole, pm);

                if (!sesionExist(misesion)) {

                    misesion = request.getSession(true);
                    misesion.setAttribute("userID", userID);
                    System.out.println("Sesiones: luego de add -> " + misesion.getAttribute("userID"));

                    //La sesion perdurara sin actividad durante 30 min.
                    misesion.setMaxInactiveInterval(1800);
                }

                break;

            case "redirect":
                HttpSession sesion= request.getSession();
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/View/Users/Add.jsp");
                request.setAttribute("User",UsersControllerView.getUser(sesion.getAttribute("userID").toString()));
                request.setAttribute("Roles", RolesControllerView.getAllRoles());
                dispatcher.forward(request, response);
                break;

            //Si lo que se quiere es Crear (proviene del formulario)
            case "create":
                crearUsuario(userID, userEmail, userName, userImg, userRole, pm);
                break;

            //Si lo que se quiere es actualizar un Usuario
            case "update":

                User user = pm.getObjectById(User.class, prevUserID);

                user.setName(userName);
                user.setEmail(userEmail);
                user.setImgUrl(userImg);
                user.setRole(new Role(userRole,true));

                break;

        }

        pm.close();
        try{
            response.sendRedirect("/users");
        }
        //Al redirigr al jsp para crear, se usa RequestDispatcher, y este entra en conflicto con sendRedirect.
        catch (IllegalStateException e){
            System.err.println("IllegalStateException: There was a double redirect.");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Redirige a doPost
        doPost(request, response);
    }

    //Revisa si un usuario existe: id -> ID del usuario (ejm. en richard@gmail.com el ID es richard)
    private boolean userExists(String userID, PersistenceManager pm){
        try{
            //Intenta buscar en el DataStore un usuario con el ID respectivo.
            User usr = pm.getObjectById(User.class, userID);

            //Si lo encuentra devuelve true (el usuario si existe)
            return true;
        } catch (JDOObjectNotFoundException exc){
            //Si no lo encuentra, se lanza una Excepción, se captura, y se devuelve false (el usuario no existe)
            return false;
        }
    }

    //Comprueba si existe una sesion: sesion -> Objeto HttpSesion que contiene la sesion actual
    private boolean sesionExist(HttpSession sesion){
        try{
            //Intenta buscar el atributo userID dentro de la sesion
            String a = sesion.getAttribute("userID").toString();
            System.out.println("Sesion existe -> " + a);
            //Si lo encuentra, la sesion si existe
            return true;
        } catch (NullPointerException e){
            //Si no, la sesion no existe
            System.out.println("Sesion no existe");
            return false;
        }
    }

    private void crearUsuario(String userID, String userEmail, String userName, String userImg, String userRole, PersistenceManager pm){

        //Revisa si el usuario con su ID ya tiene un objeto User Persistente almacenado.
        //Si no existe, crea el objeto de tipo User con los datos que se obtienen del request, y lo hace Persistente.
        if (!userExists(userID, pm)){

            //El new Role es provisional, hasta que termine la implementacion del CRUD de Role.
            User user = new User(userID, userName, userImg, userEmail, new Role(userRole,true));

            try{
                pm.makePersistent(user);
            } finally {
                System.out.println("Usuario creado con exito -> " + user);
            }

        }
    }


}
