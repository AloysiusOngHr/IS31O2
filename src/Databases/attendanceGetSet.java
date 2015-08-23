package Databases;

public class attendanceGetSet {

public int getAttendanceId() {
	return attendanceId;
}
public void setAttendanceId(int attendanceId) {
	this.attendanceId = attendanceId;
}
public String getEmpNo() {
	return empNo;
}
public void setEmpNo(String empNo) {
	this.empNo = empNo;
}
public double getTimeIn() {
	return timeIn;
}
public void setTimeIn(double timeIn) {
	this.timeIn = timeIn;
}
public double getTimeOut() {
	return timeOut;
}
public void setTimeOut(double timeOut) {
	this.timeOut = timeOut;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public int getProjectId() {
	return projectId;
}
public void setProjectId(int projectId) {
	this.projectId = projectId;
}
public double getOtHours() {
	return otHours;
}
public void setOtHours(double otHours) {
	this.otHours = otHours;
}
int attendanceId;
String empNo;
double timeIn;
double timeOut;
String date;
int projectId;
double otHours;
}
