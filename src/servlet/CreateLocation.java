package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Databases.ADatabase;
import Databases.locationGetSet;

/**
 * Servlet implementation class CreateLocation
 */
public class CreateLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		locationGetSet loc = new locationGetSet();
		String locationName = request.getParameter("lN");
		String description = request.getParameter("desc");

		loc.setLocationName(locationName);
		loc.setDescription(description);
		
		try {
			ADatabase db = new ADatabase();
			boolean done = db.createLocation(loc);
			if(done == true)
				getServletContext().getRequestDispatcher("/CreateLocSuccessful.jsp").forward(request,response);
			getServletContext().getRequestDispatcher("/CreateLocUnsuccessful.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
