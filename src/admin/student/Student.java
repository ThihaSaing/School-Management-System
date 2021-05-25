package admin.student;

public class Student 
{
	private int studentid;
	private int classid;
	private int accountid;
	private String studentname;
	private String dob;
	private String nric;
	private String phoneno;
	private String address;
	private int rollno;
	private String classname;
	
	
	public Student(int studentid,int classid,int accountid,String studentname,String dob,String nric,String phoneno,String address,int rollno)
	{
		this.studentid=studentid;
		this.classid=classid;
		this.accountid=accountid;
		this.studentname=studentname;
		this.dob=dob;
		this.nric=nric;
		this.phoneno=phoneno;
		this.address=address;
		this.rollno=rollno;
	}
	
	public Student(String classname,int studentid,int classid,int accountid,String studentname,String dob,String nric,String phoneno,String address,int rollno)
	{
		this.classname = classname;
		this.studentid=studentid;
		this.classid=classid;
		this.accountid=accountid;
		this.studentname=studentname;
		this.dob=dob;
		this.nric=nric;
		this.phoneno=phoneno;
		this.address=address;
		this.rollno=rollno;
	}
	
	
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	public int getClassid() {
		return classid;
	}
	public void setClassid(int classid) {
		this.classid = classid;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getdob() {
		return dob;
	}
	public void setdob(String dob) {
		this.dob = dob;
	}
	public String getNric() {
		return nric;
	}
	public void setNric(String nric) {
		this.nric = nric;
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
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	@Override
	public String toString() {
		return "Student [studentid=" + studentid + ", classid=" + classid + ", accountid=" + accountid
				+ ", studentname=" + studentname + ", dob=" + dob + ", nric=" + nric + ", phoneno=" + phoneno
				+ ", address=" + address + ", rollno=" + rollno + "]";
	}
	
}
