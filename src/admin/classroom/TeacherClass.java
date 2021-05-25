package admin.classroom;

public class TeacherClass {
	private int id;
	private int teacherid;
	private int subjectid;
	private int classid;
	private String teachername;
	private String classname;
	private String subjectname;

	public TeacherClass(int id, int classid, int teacherid) {
		this.id = id;
		this.classid = classid;
		this.teacherid = teacherid;
	}

	public TeacherClass(int id, int classid, int teacherid, String classname, String teachername) {
		this.id = id;
		this.classid = classid;
		this.teacherid = teacherid;
		this.classname = classname;
		this.teachername = teachername;
	}

	public TeacherClass(int classid, int teacherid, int subjectid, String classname, String teachername,
			String subjectname) {
		super();
		this.teacherid = teacherid;
		this.subjectid = subjectid;
		this.classid = classid;
		this.teachername = teachername;
		this.classname = classname;
		this.subjectname = subjectname;
	}

	public TeacherClass(int id, int classid, int teacherid, int subjectid, String classname, String teachername,
			String subjectname) {
		this.id = id;
		this.classid = classid;
		this.teacherid = teacherid;
		this.subjectid = subjectid;
		this.classname = classname;
		this.teachername = teachername;
		this.subjectname = subjectname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(int teacherid) {
		this.teacherid = teacherid;
	}

	public int getClassid() {
		return classid;
	}

	public void setClassid(int classid) {
		this.classid = classid;
	}

	public String getTeachername() {
		return teachername;
	}

	public void setTeachername(String teachername) {
		this.teachername = teachername;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
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
}
