package teacher.classInfo;

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
	
	private int subjectid;
	private String subjectname;
	private int markid;
	private int marks;
	
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
	
	public Student(int studentid,int rollno,String studentname,int subjectid,String subjectname,int markid,int marks){
		this.rollno = rollno;
		this.studentid=studentid;
		this.subjectid=subjectid;
		this.studentname=studentname;
		this.subjectname=subjectname;
		this.markid = markid;
		this.marks = marks;
	}
	
	public Student(int studentid,int subjectid,int markid,int marks){
		this.studentid=studentid;
		this.subjectid=subjectid;
		this.markid = markid;
		this.marks = marks;
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

	public int getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public int getMarkid() {
		return markid;
	}

	public void setMarkid(int markid) {
		this.markid = markid;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Student [studentid=" + studentid + ", classid=" + classid + ", accountid=" + accountid
				+ ", studentname=" + studentname + ", dob=" + dob + ", nric=" + nric + ", phoneno=" + phoneno
				+ ", address=" + address + ", rollno=" + rollno + "]";
	}
	
}
