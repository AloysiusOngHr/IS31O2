package Databases;

public class salesofassetsGetSet {

	int soaId;
	String date;
	int itemId;
	String qty;
	String remarks;
	String createdBy;
	String vendorName;
	String vendorContact;
	
	public int getSoaId() {
		return soaId;
	}
	public void setSoaId(int soaId) {
		this.soaId = soaId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getQty() {
		return qty;
	}
	public void setQty(String qty) {
		this.qty = qty;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorContact() {
		return vendorContact;
	}
	public void setVendorContact(String vendorContact) {
		this.vendorContact = vendorContact;
	}
}
