package admin.staff;

import java.util.Date;

public class Staff {
	private int staffid;
	private String staffname;
	private String position;
	private int salary;
	private String phoneno;
	private String address;
	private Date dob;
	private String nric;

	public Staff(int staffid, String staffname, String position, int salary, String phoneno, String address, Date dob,
			String nric) {
		this.staffid = staffid;
		this.staffname = staffname;
		this.position = position;
		this.salary = salary;
		this.phoneno = phoneno;
		this.address = address;
		this.dob = dob;
		this.nric = nric;
	}

	public int getStaffid() {
		return staffid;
	}

	public void setStaffid(int staffid) {
		this.staffid = staffid;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getNric() {
		return nric;
	}

	public void setNric(String nric) {
		this.nric = nric;
	}

}
