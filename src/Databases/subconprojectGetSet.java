package Databases;

public class subconprojectGetSet {
	
	int subprojectId;
	int projectId;
	int subconId;
	double price;
	String payStatus;
	String payDate;
	
	public int getSubprojectId() {
		return subprojectId;
	}
	public void setSubprojectId(int subprojectId) {
		this.subprojectId = subprojectId;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getSubconId() {
		return subconId;
	}
	public void setSubconId(int subconId) {
		this.subconId = subconId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	
}
