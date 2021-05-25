package student;

public class Student {

	private int studentid;
	private int accountid;
	private int classid;
	private int rollno;
	private String name;
	private String dob;
	private String address;
	private String phone;
	private String nric;
	
	private String className;
	
	public Student (int studentid, int accountid, int classid, int rollno, String name, String dob, String address, String phone, String nric) {
		
		this.studentid = studentid;
		this.accountid = accountid;
		this.classid = classid;
		this.rollno = rollno;
		this.name = name;
		this.dob = dob;
		this.address = address;
		this.phone = phone;
		this.nric = nric;
	}
	
	public Student (int studentid, int accountid, int classid, int rollno, String name, String dob, String address, String phone, String nric, String className) {
		
		this.studentid = studentid;
		this.accountid = accountid;
		this.classid = classid;
		this.rollno = rollno;
		this.name = name;
		this.dob = dob;
		this.address = address;
		this.phone = phone;
		this.nric = nric;
		this.className = className;
	}
	
	public Student (String name) {
		this.name = name;
	}
	
	public int getStudentid() {
		return studentid;
	}
	
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	
	public int getAccountid() {
		return accountid;
	}
	
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	
	public int getClassid() {
		return classid;
	}
	
	public void setClassid(int classid) {
		this.classid = classid;
	}
	
	public int getRollno() {
		return rollno;
	}
	
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDob() {
		return dob;
	}
	
	public void setDob(String dob) {
		this.dob = dob;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getNric() {
		return nric;
	}
	
	public void setNric(String nric) {
		this.nric = nric;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
