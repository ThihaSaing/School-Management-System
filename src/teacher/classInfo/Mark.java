package teacher.classInfo;

public class Mark {
	private int markid;
	private int studentid;
	private int subjectid;
	private int marks;
	
	public Mark(int markid, int studentid, int subjectid, int marks){
		this.markid = markid;
		this.studentid = studentid;
		this.subjectid = subjectid;
		this.marks = marks;
	}

	public int getMarkid() {
		return markid;
	}

	public void setMarkid(int markid) {
		this.markid = markid;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public int getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Mark [markid=" + markid + ", studentid=" + studentid + ", subjectid=" + subjectid + ", marks=" + marks
				+ "]";
	}
}
