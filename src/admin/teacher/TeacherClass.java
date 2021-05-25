package admin.teacher;

public class TeacherClass {
	private int id;
	private int teacherid;
	private int classid;
	private String teachername;
	private String classname;
	
	public TeacherClass(int id,int classid, int teacherid){
		this.id = id;
		this.classid = classid;
		this.teacherid = teacherid;
	}
	
	public TeacherClass(int id,int classid, int teacherid,String classname,String teachername){
		this.id = id;
		this.classid = classid;
		this.teacherid = teacherid;
		this.classname = classname;
		this.teachername = teachername;
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
}
