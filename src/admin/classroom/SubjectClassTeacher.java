package admin.classroom;

public class SubjectClassTeacher {
	private int id;
	private int subjectid;
	private int classid;
	private int teacherid;
	private String subjectname;
	private String classname;
	private String teachername;

	public SubjectClassTeacher(int id, int subjectid, int classid, int teacherid) {
		super();
		this.id = id;
		this.subjectid = subjectid;
		this.classid = classid;
		this.teacherid = teacherid;
	}

	public SubjectClassTeacher(int id, int subjectid, int classid, int teacherid, String subjectname, String classname,
			String teachername) {
		super();
		this.id = id;
		this.subjectid = subjectid;
		this.classid = classid;
		this.teacherid = teacherid;
		this.subjectname = subjectname;
		this.classname = classname;
		this.teachername = teachername;
	}

	public int getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSubjectid() {
		return subjectid;
	}

	public void setSubjectid(int subjectid) {
		this.subjectid = subjectid;
	}

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

}
