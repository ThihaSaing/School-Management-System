package admin.teacher;

import java.util.Date;

public class Teacher {

	private int teacherId;
	private int subjectId;
	private int accountId;
	private String teacherName;
	private Date teacherDoB;
	private String teacherAddress;
	private String teacherPhoneNo;
	private String teacherNRC;

	public Teacher(String teacherName, Date teacherDoB, String teacherAddress, String teacherPhoneNo,
			String teacherNRC) {
		super();
		this.teacherName = teacherName;
		this.teacherDoB = teacherDoB;
		this.teacherAddress = teacherAddress;
		this.teacherPhoneNo = teacherPhoneNo;
		this.teacherNRC = teacherNRC;
	}

	public Teacher(int teacherId, String teacherName, Date teacherDoB, String teacherAddress, String teacherPhoneNo,
			String teacherNRC) {
		super();
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.teacherDoB = teacherDoB;
		this.teacherAddress = teacherAddress;
		this.teacherPhoneNo = teacherPhoneNo;
		this.teacherNRC = teacherNRC;
	}

	public Teacher(int teacherId, int subjectId, int accountId, String teacherName, Date teacherDoB,
			String teacherAddress, String teacherPhoneNo, String teacherNRC) {
		super();
		this.teacherId = teacherId;
		this.subjectId = subjectId;
		this.accountId = accountId;
		this.teacherName = teacherName;
		this.teacherDoB = teacherDoB;
		this.teacherAddress = teacherAddress;
		this.teacherPhoneNo = teacherPhoneNo;
		this.teacherNRC = teacherNRC;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Date getTeacherDoB() {
		return teacherDoB;
	}

	public void setTeacherDoB(Date teacherDoB) {
		this.teacherDoB = teacherDoB;
	}

	public String getTeacherAddress() {
		return teacherAddress;
	}

	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}

	public String getTeacherPhoneNo() {
		return teacherPhoneNo;
	}

	public void setTeacherPhoneNo(String teacherPhoneNo) {
		this.teacherPhoneNo = teacherPhoneNo;
	}

	public String getTeacherNRC() {
		return teacherNRC;
	}

	public void setTeacherNRC(String teacherNRC) {
		this.teacherNRC = teacherNRC;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherName=" + teacherName + ", teacherDoB=" + teacherDoB
				+ ", teacherAddress=" + teacherAddress + ", teacherPhoneNo=" + teacherPhoneNo + ", teacherNRC="
				+ teacherNRC + "]";
	}

}
