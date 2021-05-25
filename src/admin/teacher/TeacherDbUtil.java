package admin.teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public class TeacherDbUtil {

	private DataSource dataSource;

	public TeacherDbUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public List<Teacher> getTeachers() throws Exception {
		List<Teacher> teachers = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = dataSource.getConnection();
			String sql = "select * from teacher";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				int teacherId = myRs.getInt("teacherid");
				int subjectId = myRs.getInt("subjectid");
				int accountId = myRs.getInt("accountid");
				String teacherName = myRs.getString("teachername");
				Date teacherDoB = myRs.getDate("dob");
				String teacherAdd = myRs.getString("address");
				String teacherPh = myRs.getString("phoneno");
				String teacherNRIC = myRs.getString("nric");

				teachers.add(new Teacher(teacherId, subjectId, accountId, teacherName, teacherDoB, teacherAdd,
						teacherPh, teacherNRIC));
				// teachers.add(new Teacher(teacherId, teacherName, teacherDoB, teacherAdd,
				// teacherPh, teacherNRIC));
			}
			return teachers;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myConn != null) {
				myConn.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myRs != null) {
				myRs.close();
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	public void addTeacher(Teacher theTeacher) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "insert into teacher (subjectid, accountid, teachername, dob, address, phoneno, nric) values (?,NULL,?,?,?,?,?)";

			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, theTeacher.getSubjectId());
			myStmt.setString(2, theTeacher.getTeacherName());
			myStmt.setDate(3, (java.sql.Date) theTeacher.getTeacherDoB());
			myStmt.setString(4, theTeacher.getTeacherAddress());
			myStmt.setString(5, theTeacher.getTeacherPhoneNo());
			myStmt.setString(6, theTeacher.getTeacherNRC());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
	}

	public Teacher getTeacher(String theTeacherId) throws Exception {
		Teacher theTeacher = null;

		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int teacherId;

		try {
			teacherId = Integer.parseInt(theTeacherId);

			myConn = dataSource.getConnection();
			String sql = "select * from teacher where teacherid=?";
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, teacherId);
			myRs = myStmt.executeQuery();

			if (myRs.next()) {
				int subjectid = myRs.getInt("subjectid");
				int accountid = myRs.getInt("accountid");
				String teacherName = myRs.getString("teachername");
				Date teacherDoB = myRs.getDate("dob");
				String teacherAdd = myRs.getString("address");
				String teacherPh = myRs.getString("phoneno");
				String teacherNRIC = myRs.getString("nric");

				theTeacher = new Teacher(teacherId, subjectid, accountid, teacherName, teacherDoB, teacherAdd, teacherPh, teacherNRIC);
			} else {
				throw new Exception("Could not find teacher id: " + teacherId);
			}
			return theTeacher;
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	public List<Subject> getSubjects() throws SQLException {
		List<Subject> subjectList = new ArrayList<>();
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		try {
			myConn = dataSource.getConnection();
			String sql = "select * from subject order by subjectid";
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);
			while (myRs.next()) {
				int id = myRs.getInt("subjectid");
				String name = myRs.getString("subjectname");
				subjectList.add(new Subject(id, name));
			}
		} finally {
			close(myConn, myStmt, myRs);
		}
		return subjectList;
	}

	public void updateTeacher(Teacher theTeacher, int subjectid) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			myConn = dataSource.getConnection();

			String sql = "update teacher set teachername=?, subjectid=?, dob=?, address=?, phoneno=?, nric=? where teacherid=?";
			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, theTeacher.getTeacherName());
			myStmt.setInt(2, subjectid);
			myStmt.setDate(3, (java.sql.Date) theTeacher.getTeacherDoB());
			myStmt.setString(4, theTeacher.getTeacherAddress());
			myStmt.setString(5, theTeacher.getTeacherPhoneNo());
			myStmt.setString(6, theTeacher.getTeacherNRC());
			myStmt.setInt(7, theTeacher.getTeacherId());

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
	}

	public void deleteTeacher(String theTeacherId) throws Exception {

		Connection myConn = null;
		PreparedStatement myStmt = null;

		try {
			int teacherId = Integer.parseInt(theTeacherId);

			myConn = dataSource.getConnection();

			String sql = "delete from teacher where teacherid=?";

			myStmt = myConn.prepareStatement(sql);

			myStmt.setInt(1, teacherId);

			myStmt.execute();
		} finally {
			close(myConn, myStmt, null);
		}
	}

}
