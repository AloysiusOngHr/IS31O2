package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Databases.ADatabase;
import Databases.containerGetSet;
import Databases.supplierListGetSet;

/**
 * Servlet implementation class addItem
 */
public class addItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addItem() {
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
		supplierListGetSet sL = new supplierListGetSet();
		int minQty = Integer.parseInt(request.getParameter("mQ"));
		int leadTime = Integer.parseInt(request.getParameter("lT"));
		double price = Double.parseDouble(request.getParameter("price"));
		String itemId = request.getParameter("item");
		int supplierId = Integer.parseInt(request.getParameter("id"));
		sL.setItemId(itemId);
		sL.setLeadTime(leadTime);
		sL.setMinQty(minQty);
		sL.setPrice(price);
		sL.setSupplierId(supplierId);
	
		try {
			ADatabase db = new ADatabase();
			boolean done = db.createSupplierList(sL);
			if(done == true)
				getServletContext().getRequestDispatcher("/SuccessfulItemAdded.jsp").forward(request,response);
			getServletContext().getRequestDispatcher("/UnsuccessfulItemAdded.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
