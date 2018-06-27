package informs;
import java.io.IOException;
import javax.servlet.http.*;
import javax.servlet.*;
import javax.jdo.PersistenceManager;
import model.entity.*;;

@SuppressWarnings("serial")
public class AddController extends HttpServlet {

 public void doGet(HttpServletRequest request, HttpServletResponse
response)
 throws ServletException, IOException {
	 // create the persistence manager instance
	
		if(request.getParameter("action").equals("acc")) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		 // create the new account
		 Inform a = new Inform(
		 request.getParameter("tipo"),
		 request.getParameter("texto"),
		 request.getParameter("rol"),
		 request.getParameter("gender"),
		 request.getParameter("name") );

		 // persist the entity
		 try {
		 pm.makePersistent(a);
		 } finally {
		 pm.close();
		 }


	//	response.sendRedirect("index?action=mostrar&accountId="+a.getId());//
		 response.sendRedirect("index");
		 // display the account details and opportunities
		 }
		else{
			 request.getRequestDispatcher("WEB-INF/Views/add.jsp").forward(request, response);
		}
	 
 }
}