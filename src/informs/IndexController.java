package informs;
import java.io.IOException;
import javax.servlet.http.*;
import java.util.List;
import javax.servlet.*;
import javax.jdo.PersistenceManager;
import model.entity.*;


import com.google.appengine.api.datastore.KeyFactory;
@SuppressWarnings("serial")
public class IndexController extends HttpServlet {

 public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		 String query = "select from " + Inform.class.getName();
		 List<Inform> informs = (List<Inform>)pm.newQuery(query).execute();
		 
		  request.setAttribute("informs", informs);

		  // forward the request to the jsp
		  RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/index.jsp");
		  dispatcher.forward(request, response);

		 
	 }

}
	 
