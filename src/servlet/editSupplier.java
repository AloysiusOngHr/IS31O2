package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Databases.*;

/**
 * Servlet implementation class editSupplier
 */
public class editSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editSupplier() {
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
		String id = request.getParameter("id");
		String address = request.getParameter("add");
		String poCode = request.getParameter("poCode");
		String country = request.getParameter("country");
		String conPer = request.getParameter("conPerson");
		String conNo = request.getParameter("contact");
		System.out.println(id);

		try {
			ADatabase db = new ADatabase();
			boolean done = db.editSupplier(id, address, poCode, country, conPer, conNo);
			if(done == true)
				getServletContext().getRequestDispatcher("/SupplierEditedSuccessfully.jsp").forward(request,response);
			getServletContext().getRequestDispatcher("/SupplierEditedUnsuccessfully.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
