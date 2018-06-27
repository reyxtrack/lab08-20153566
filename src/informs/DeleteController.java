package informs;
import java.io.IOException;
import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import model.entity.*;

@SuppressWarnings("serial")
public class DeleteController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		 PersistenceManager pm = PMF.get().getPersistenceManager();
		 Key k = KeyFactory.createKey(Inform.class.getSimpleName(), new Long(req.getParameter("action")).longValue());
		 Inform a = pm.getObjectById(Inform.class, k);
		 try{
		 pm.deletePersistent(a);
				 
				 /* --------------------------------------------------------
				 */
				//@SuppressWarnings("unchecked")
				//List<Persona> personas = (List<Persona>) q.execute(color);
				//for(Persona p: personas){
					//pm.deletePersistent(p);
				//}
				/* ---------------------------------------------------------
				 */
				resp.sendRedirect("/index");
			}catch(Exception e){
					System.out.println(e);
					resp.getWriter().println("No se pudo podido borrar informe.");
					resp.sendRedirect("/index");
			}finally{

				pm.close();
			}			
		
				
	}
}

