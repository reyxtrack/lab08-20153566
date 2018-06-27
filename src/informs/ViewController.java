package informs;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.jdo.PersistenceManager;
import model.entity.*;


import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
@SuppressWarnings("serial")
public class ViewController extends HttpServlet {

 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		 Key k = KeyFactory.createKey(Inform.class.getSimpleName(), new Long(request.getParameter("action")).longValue());
		 Inform a = pm.getObjectById(Inform.class, k);
		 request.setAttribute("inform",a);
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/view.jsp");
				 dispatcher.forward(request, response);


		  
 
 }}
