package Databases;

public class InventoryRequest {

	int irId;
	String date;
	int qty;
	String returnDate;
	String status;
	public int getIrId() {
		return irId;
	}
	public void setIrId(int irId) {
		this.irId = irId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
