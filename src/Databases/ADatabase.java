package Databases;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.sql.*;
import javax.naming.*;
import servlet.*;

import Databases.*;
import servlet.*;
import java.util.*;
import java.util.Date;

public class ADatabase {

	Connection con;
	private boolean conFree = true;
	public static String day;
	public static int time;
	public static int duration;

	public ADatabase() throws Exception {
		try {
			DB db = new DB();
			con = db.getConnection();
		} catch (Exception ex) {
			System.out.println("Exception in BookDBAO: " + ex);
			throw new Exception("Couldn't open connection to database: " +
					ex.getMessage());
		}
	}

	public void remove() {
		try {
			if (con!=null) con.close();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	protected synchronized Connection getConnection() {
		while (conFree == false) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		conFree = false;
		notify();

		return con;
	}

	protected synchronized void releaseConnection() {
		while (conFree == true) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		conFree = true;
		notify();
	}

	public static String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		return dateFormat.format(date);
	} 

	public boolean createInventory(InventoryGetSet inv) {
		boolean status = false;
		boolean have = haveInventory(inv);
		if(have == false){
			try {
				String sql = "insert into inventory (inventoryId, name, type, description, unit, rentPrice, qty) values (?,?,?,?,?,?,?)";
				getConnection();
				PreparedStatement prepStmt = con.prepareStatement(sql);
				prepStmt.setString(1,inv.getInventoryId());
				prepStmt.setString(2, inv.getName());
				prepStmt.setString(3, inv.getType());
				prepStmt.setString(4, inv.getDescription());
				prepStmt.setString(5, inv.getUnit());
				//prepStmt.setDouble(5, inv.getPurchasePrice());
				prepStmt.setDouble(6, inv.getRentPrice());
				prepStmt.setInt(7, inv.getQty());

				int count = prepStmt.executeUpdate();
				if (count > 0) status = true;
				prepStmt.close();
				releaseConnection();
			} catch (SQLException ex) {
				releaseConnection();
				ex.printStackTrace();
			}
		}
		return status;
	}

	public boolean haveInventory(InventoryGetSet inv){
		String haveInv = inv.getInventoryId();
		boolean have = false;
		try {
			String selectStatement = "select * from inventory WHERE inventoryId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, haveInv);
			ResultSet rs = prepStmt.executeQuery();

			System.out.println(rs.getFetchSize());

			if(rs.getFetchSize()>0)
				have = true;

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return have;
	}	

	public List getInventory(String result) {
		ArrayList list1 = new ArrayList();
		try {
			String selectStatement = "select * from inventory WHERE inventoryId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1,  "%" + result + "%");
			ResultSet rs = prepStmt.executeQuery();


			while (rs.next()) {
				InventoryGetSet rgs = new InventoryGetSet();
				rgs.setInventoryId(rs.getString("inventoryId"));
				rgs.setName(rs.getString("name"));
				rgs.setType(rs.getString("type"));
				rgs.setDescription(rs.getString("description"));
				rgs.setUnit(rs.getString("Unit"));
				rgs.setRentPrice(rs.getDouble("rentPrice"));
				rgs.setQty(rs.getInt("qty"));	
				list1.add(rgs);
			} 

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return list1;
	}

	public boolean createContainer(containerGetSet cont) {
		boolean status = false;
		boolean have = haveContainer(cont);
		if(have == false){
			try {

				String sql = "insert into container (name, length, breath, height, weight) values (?,?,?,?,?)";
				getConnection();
				PreparedStatement prepStmt = con.prepareStatement(sql);
				prepStmt.setString(1,cont.getName());
				prepStmt.setDouble(2, cont.getLength());
				prepStmt.setDouble(3, cont.getBreath());
				prepStmt.setDouble(4, cont.getHeight());
				prepStmt.setDouble(5, cont.getWeight());

				int count = prepStmt.executeUpdate();
				if (count > 0) status = true;
				prepStmt.close();
				releaseConnection();
			} catch (SQLException ex) {
				releaseConnection();
				ex.printStackTrace();
			}
		}
		return status;
	}

	public boolean haveContainer(containerGetSet cont){
		int haveCon = cont.getContainerId();
		boolean have = false;
		try {
			String selectStatement = "select * from container WHERE contianerId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, haveCon);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.getFetchSize()>0)
				have = true;

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return have;
	}	

	public List getContainer(String result) {
		ArrayList list1 = new ArrayList();
		try {
			String selectStatement = "select * from container WHERE containerId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1,  "%" + result + "%");
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				containerGetSet rgs = new containerGetSet();
				rgs.setContainerId(rs.getInt("containerId"));
				rgs.setName(rs.getString("name"));
				rgs.setBreath(rs.getDouble("breath"));
				rgs.setHeight(rs.getDouble("height"));
				rgs.setLength(rs.getDouble("length"));
				rgs.setWeight(rs.getDouble("weight"));
				//rgs.setLocation(rs.getString("location"));
				list1.add(rgs);
			} 

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return list1;
	}

	public boolean createSupplier(supplierGetSet sup) {
		boolean status = false;
		boolean have = haveSupplier(sup);
		if(have == false){
			try {

				String sql = "insert into supplier (companyName, address, poCode, contactNo, country, contactPerson) values (?,?,?,?,?,?)";
				getConnection();
				PreparedStatement prepStmt = con.prepareStatement(sql);
				prepStmt.setString(1,sup.getCompanyName());
				prepStmt.setString(2, sup.getAddress());
				prepStmt.setString(3, sup.getPoCode());
				prepStmt.setString(4, sup.getCountry());
				prepStmt.setString(5, sup.getContactPerson());
				prepStmt.setString(6, sup.getContactNo());

				int count = prepStmt.executeUpdate();
				if (count > 0) status = true;
				prepStmt.close();
				releaseConnection();
			} catch (SQLException ex) {
				releaseConnection();
				ex.printStackTrace();
			}
		}
		return status;
	}

	public boolean haveSupplier(supplierGetSet sup){
		int haveSup = sup.getSupplierId();
		boolean have = false;
		try {
			String selectStatement = "select * from supplier WHERE supplierId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setInt(1, haveSup);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.getFetchSize()>0)
				have = true;

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return have;
	}

	public List getSupplier(String result) {
		ArrayList list1 = new ArrayList();
		try {
			String selectStatement = "select * from supplier WHERE supplierId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1,  "%" + result + "%");
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				supplierGetSet rgs = new supplierGetSet();
				rgs.setSupplierId(rs.getInt("supplierId"));
				rgs.setCompanyName(rs.getString("companyName"));
				rgs.setAddress(rs.getString("address"));
				rgs.setPoCode(rs.getString("poCode"));
				rgs.setContactNo(rs.getString("contactNo"));
				rgs.setCountry(rs.getString("country"));
				rgs.setContactPerson(rs.getString("contactPerson"));
				list1.add(rgs);
			} 

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return list1;
	}

	public List getASupplier(String result) {
		ArrayList list1 = new ArrayList();
		try {
			String selectStatement = "select * from supplier WHERE supplierId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, result);
			ResultSet rs = prepStmt.executeQuery();

			while (rs.next()) {
				supplierGetSet rgs = new supplierGetSet();
				rgs.setSupplierId(rs.getInt("supplierId"));
				rgs.setCompanyName(rs.getString("companyName"));
				rgs.setAddress(rs.getString("address"));
				rgs.setPoCode(rs.getString("poCode"));
				rgs.setContactNo(rs.getString("contactNo"));
				rgs.setCountry(rs.getString("country"));
				rgs.setContactPerson(rs.getString("contactPerson"));
				list1.add(rgs);
			} 

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return list1;
	}

	public List getLocation(String result) {
		ArrayList list1 = new ArrayList();
		try {
			String selectStatement = "select * from location WHERE locationName like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1,  "%" + result + "%");
			ResultSet rs = prepStmt.executeQuery();


			while (rs.next()) {
				locationGetSet rgs = new locationGetSet();
				rgs.setLocationId(rs.getInt("locationId"));
				rgs.setLocationName(rs.getString("locationName"));
				rgs.setDescription(rs.getString("description"));
				list1.add(rgs);
			} 

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return list1;
	}

	public boolean createLocation(locationGetSet loc) {
		boolean status = false;
		boolean have = haveLocation(loc);
		if(have == false){
			try {

				String sql = "insert into location (locationName, description) values (?,?)";
				getConnection();
				PreparedStatement prepStmt = con.prepareStatement(sql);
				prepStmt.setString(1,loc.getLocationName());
				prepStmt.setString(2, loc.getDescription());

				int count = prepStmt.executeUpdate();
				if (count > 0) status = true;
				prepStmt.close();
				releaseConnection();
			} catch (SQLException ex) {
				releaseConnection();
				ex.printStackTrace();
			}
		}
		return status;
	}

	public boolean haveLocation(locationGetSet loc){
		String haveLoc = loc.getLocationName();
		boolean have = false;
		try {
			String selectStatement = "select * from location WHERE locationName like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, haveLoc);
			ResultSet rs = prepStmt.executeQuery();

			if(rs.getFetchSize()>0)
				have = true;

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return have;
	}

	public List getSupplierList(String result) {
		ArrayList list1 = new ArrayList();
		try {
			String selectStatement = "select * from supplierList WHERE supplierId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1,  result);
			ResultSet rs = prepStmt.executeQuery();


			while (rs.next()) {
				supplierListGetSet rgs = new supplierListGetSet();
				rgs.setSupplierListId(rs.getInt("supplierListId"));
				rgs.setItemId(rs.getString("itemId"));
				rgs.setMinQty(rs.getInt("minQty"));
				rgs.setLeadTime(rs.getInt("leadTime"));
				rgs.setPrice(rs.getDouble("price"));
				list1.add(rgs);
			} 

			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return list1;
	}

	public boolean editSupplier(String id, String address, String poCode, String country, String conPer, String conNo) {
		boolean status = false;
		System.out.println(id);
		try {
			String sql = "UPDATE supplier SET address = ?, poCode = ?, country = ?, contactPerson = ?, contactNo = ? WHERE supplierId = ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setString(1, address);
			prepStmt.setString(2, poCode);
			prepStmt.setString(3, country);
			prepStmt.setString(4, conPer);
			prepStmt.setString(5, conNo);
			prepStmt.setString(6, id);

			System.out.println(id);
			System.out.println(id);
			System.out.println(id);
			int count = prepStmt.executeUpdate();
			if (count > 0) status = true;
			prepStmt.close();
			releaseConnection();
		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return status;
	}

	public List getItemList(String supId){
		ArrayList list1 = new ArrayList();
		try {
			//add supplier list
			ArrayList check = (ArrayList) getItemList();
			ArrayList item = new ArrayList();
			for(int i = 0; i < check.size(); i++){
				ArrayList temp = (ArrayList) check.get(i);
				String m = (String) temp.get(1);
				if(m.equals(supId))
					item.add(temp.get(0));
			}
			
			String selectStatement = "select * from inventory WHERE inventoryId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1,  "%");
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				ArrayList rgs = new ArrayList();
				String id = rs.getString("inventoryId");
				rgs.add(id);
				rgs.add(rs.getString("name"));
				boolean nohave = true;
				for (int j = 0; j < item.size();j++){
					if(id.equals(item.get(j))){
						nohave = false;
						break;
					}
				}
				
				if(nohave)
					list1.add(rgs);
			} 
			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return list1;
	}

	public boolean createSupplierList(supplierListGetSet sl) {
		boolean status = false;

		try {

			String sql = "insert into supplierList (minQty, leadTime, price, supplierId, itemId) values (?,?,?,?,?)";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(sql);
			prepStmt.setInt(1,sl.getMinQty());
			prepStmt.setInt(2, sl.getLeadTime());
			prepStmt.setDouble(3, sl.getPrice());
			prepStmt.setInt(4, sl.getSupplierId());
			prepStmt.setString(5, sl.getItemId());

			int count = prepStmt.executeUpdate();
			if (count > 0) status = true;
			prepStmt.close();
			releaseConnection();
		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}

		return status;
	}
	
	public List getItemList(){
		ArrayList list1 = new ArrayList();
		try {
			String selectStatement = "select * from supplierList WHERE supplierListId like ?";
			getConnection();
			PreparedStatement prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1,  "%");
			ResultSet rs = prepStmt.executeQuery();
			while (rs.next()) {
				ArrayList rgs = new ArrayList();
				String id = rs.getString("itemId");
				rgs.add(id);
				rgs.add(rs.getString("supplierId"));
                list1.add(rgs);
			} 
			prepStmt.close();
			releaseConnection();

		} catch (SQLException ex) {
			releaseConnection();
			ex.printStackTrace();
		}
		return list1;
	}
}

