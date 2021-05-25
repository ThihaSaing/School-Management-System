package student;

public class Teacher {
	
	private String subjectName;
	private String teacherName;
	
	public Teacher(String subjectName, String teacherName) {
		
		this.subjectName = subjectName;
		this.teacherName = teacherName;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
}
