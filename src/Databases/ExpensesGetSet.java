package Databases;

public class ExpensesGetSet {
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public int getExpensesId() {
		return expensesId;
	}
	public void setExpensesId(int expensesId) {
		this.expensesId = expensesId;
	}
	int expensesId;
	String name;
	String date;
	String createdBy;
	String amount;
	String remarks;

}
