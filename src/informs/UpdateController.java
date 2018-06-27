package informs;
import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import model.entity.*;


@SuppressWarnings("serial")
public class UpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");		
		if(request.getParameter("action").equals("acc")) {
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		 Key k = KeyFactory.createKey(Inform.class.getSimpleName(), new Long(request.getParameter("id")).longValue());
		 Inform a = pm.getObjectById(Inform.class, k);
		 a.setName(request.getParameter("nombre"));
		 a.setGender(request.getParameter("genero"));
		 a.setRol(request.getParameter("rol"));
		 a.setType(request.getParameter("tipo"));
		 a.setInform(request.getParameter("texto"));
		 
		response.sendRedirect("/index");
	}
		else{
			 PersistenceManager pm = PMF.get().getPersistenceManager();
			 Key k = KeyFactory.createKey(Inform.class.getSimpleName(), new Long(request.getParameter("action")).longValue());
			 Inform a = pm.getObjectById(Inform.class, k);
			 request.setAttribute("inform",a);
			 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/update.jsp");
					 dispatcher.forward(request, response);

		}
	}
}

