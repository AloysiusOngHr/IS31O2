package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Databases.ADatabase;
import Databases.containerGetSet;

/**
 * Servlet implementation class CreateContainer
 */
public class CreateContainer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateContainer() {
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
		containerGetSet cont = new containerGetSet();
		String name = request.getParameter("name");
		double len = Double.parseDouble(request.getParameter("len"));
		double breath = Double.parseDouble(request.getParameter("breath"));
		double height = Double.parseDouble(request.getParameter("height"));
		double weight = Double.parseDouble(request.getParameter("weight"));
	
		cont.setName(name);
		cont.setLength(len);
		cont.setBreath(breath);
		cont.setHeight(height);
		cont.setWeight(weight);
	
		try {
			ADatabase db = new ADatabase();
			boolean done = db.createContainer(cont);
			if(done == true)
				getServletContext().getRequestDispatcher("/CreateContSuccessful.jsp").forward(request,response);
			getServletContext().getRequestDispatcher("/CreateContUnsuccessful.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
