package admin.classroom;

public class SubjectClass {
	private int id;
	private int subjectid;
	private int classid;
	private String subjectname;
	private String classname;
	
	public SubjectClass(int id,int subjectid,int classid){
		this.id = id;
		this.subjectid = subjectid;
		this.classid = classid;
	}
	
	public SubjectClass(int id,int subjectid,int classid,String subjectname,String classname){
		this.id = id;
		this.subjectid = subjectid;
		this.classid = classid;
		this.subjectname = subjectname;
		this.classname = classname;
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
