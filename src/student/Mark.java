package student;

public class Mark {
	
	public String subjectname;
	public int mark;
	
	public Mark(String subjectname, int mark) {
		this.subjectname = subjectname;
		this.mark = mark;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

}
