package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Databases.ADatabase;
import Databases.supplierGetSet;

/**
 * Servlet implementation class CreateSupplier
 */
public class CreateSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateSupplier() {
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
		supplierGetSet sup = new supplierGetSet();
		String name = request.getParameter("cn");
		String address = request.getParameter("add");
		String poCode = request.getParameter("po");
		String country = request.getParameter("country");
		String conPer = request.getParameter("conPer");
		String conNo = request.getParameter("ConNo");
		
		sup.setCompanyName(name);
		sup.setAddress(address);
		sup.setPoCode(poCode);
		sup.setCountry(country);
		sup.setContactPerson(conPer);
		sup.setContactNo(conNo);

		try {
			ADatabase db = new ADatabase();
			boolean done = db.createSupplier(sup);
			if(done == true)
				getServletContext().getRequestDispatcher("/CreateSupSuccessful.jsp").forward(request,response);
			getServletContext().getRequestDispatcher("/CreateSupUnsuccessful.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
