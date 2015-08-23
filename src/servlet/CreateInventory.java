package servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Databases.ADatabase;
import Databases.InventoryGetSet;

/**
 * Servlet implementation class CreateInventory
 */
public class CreateInventory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CreateInventory() {
        // TODO Auto-generated constructor stub
    	super();
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
		//DateFormat dateFormat = new SimpleDateFormat("yyyy");
		//Date date = new Date();
		//String dateYear = dateFormat.format(date);
		//int yearDate = Integer.parseInt(dateYear);
		InventoryGetSet inv = new InventoryGetSet();
		String ir = request.getParameter("iR");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		String unit = request.getParameter("unit");
		int qty = 0;
		//double purchasePrice  = Double.parseDouble(request.getParameter("purchasePrice"));
		double rentPrice = Double.parseDouble(request.getParameter("rentPrice"));
		
		inv.setInventoryId(ir);
		inv.setName(name);
		inv.setType(type);
		inv.setDescription(description);
		inv.setUnit(unit);
		//inv.setPurchasePrice(purchasePrice);
		inv.setRentPrice(rentPrice);
		inv.setQty(qty);
	
		try {
			ADatabase db = new ADatabase();
			boolean done = db.createInventory(inv);
			if(done == true)
				getServletContext().getRequestDispatcher("/CreateInvSuccess.jsp").forward(request,response);
			getServletContext().getRequestDispatcher("/CreateInvUnsuccessful.jsp").forward(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
