package teacher.classInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class TeacherClassInfoDbUtil {
	
	private Connection connection;
	
	public TeacherClassInfoDbUtil(DataSource dataSource) {

		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<TeacherClass> getTeacherClassInfoByTeacherId(int teacherid){
		List<TeacherClass> teacherClassDataList = new ArrayList<TeacherClass>();
		try {
			String query = "Select tc.id,t.teacherid,t.teachername,c.classid,c.classname,s.subjectid,s.subjectname from teacherclass tc inner join class c inner join teacher t  inner join subject s WHERE tc.classid = c.classid AND t.teacherid = tc.teacherid AND t.subjectid = s.subjectid AND t.teacherid = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, teacherid);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int subjectid = resultSet.getInt("subjectid");
				int classid = resultSet.getInt("classid");
				String subjectname = resultSet.getString("subjectname");
				String classname = resultSet.getString("classname");
				String teachername = resultSet.getString("teachername");
				teacherClassDataList.add(new TeacherClass(id,classid,teacherid,subjectid,classname,teachername,subjectname));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherClassDataList;
	}
	
	public List<Student> getStudentListByClassId(int id){
		List<Student> studentList = new ArrayList<Student>();
		try{
			String query = "Select * from Student where classid = ? order by rollno";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int studentid = resultSet.getInt("studentid");
				int classid = resultSet.getInt("classid");
				int accountid = resultSet.getInt("accountid");
				String studentname = resultSet.getString("studentname");
				String dob = resultSet.getString("dob");
				String nric = resultSet.getString("nric");
				String phoneno = resultSet.getString("phoneno");
				String address = resultSet.getString("address");
				int rollno = resultSet.getInt("rollno");
				studentList.add(new Student(studentid,classid,accountid,studentname,dob,nric,phoneno,address,rollno));
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return studentList;
	}
	
	public String getStudentNamebyStudentID(int studentid) throws SQLException{
		String studentname = "";
		try{
			String query = "Select studentname from Student where studentid = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentid);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				studentname = resultSet.getString("studentname");
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return studentname;
	}
	
	public int getSubjectIdByTeacherId(int teacherid){
		int subjectid = 0;
		try{
			String query = "Select subjectid from Teacher where teacherid = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, teacherid);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				subjectid = resultSet.getInt("subjectid");
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return subjectid;
	}
	
	public int getMarkIdBySubjectIdAndStudentId(int studentid,int subjectid){
		int markid = 0;
		try{
			String query = "Select markid from mark where studentid = ? and subjectid = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentid);
			statement.setInt(2, subjectid);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				markid = resultSet.getInt("markid");
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return markid;
	}
	
	public String getSubjectNameBySubjectId(int subjectid){
		String subjectname = "";
		try{
			String query = "Select subjectname from subject where subjectid = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, subjectid);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				subjectname = resultSet.getString("subjectname");
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return subjectname;
	}
	
	public List<Student> getMarks(int studentid,int classid){
		List<Student> studentList = new ArrayList<Student>();
		try{
			String query = "SELECT distinct z1.studentname,z1.rollno,z1.subjectname,z1.subjectid,z1.markid,z1.marks from (SELECT s.classid,r0.subjectname AS subjectname, r0.subjectid AS subjectid,r1.markid AS markid,r1.marks,s.studentname,s.rollno FROM (SELECT DISTINCT subjectid,subjectname FROM subject) r0 LEFT JOIN mark r1 ON r1.subjectid = r0.subjectid and r1.studentid = ? inner join student s on s.studentid = r1.studentid and s.classid = ? ) z1 inner join subjectclass sc on sc.subjectid = z1.subjectid and sc.classid =z1.classid";
			//PreparedStatement statement = this.connection.prepareCall("{CALL getmarks(1,1,@studentname,@java,@php,@c,@ruby,@asp)}");
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, studentid);
			statement.setInt(2,classid);
			
			ResultSet resultSet = statement.executeQuery();
			System.out.println(statement.toString());
			
			while (resultSet.next()) {
				int rollno = resultSet.getInt("rollno");
				String studentname = resultSet.getString("studentname");
				String subjectname = resultSet.getString("subjectname");
				int marks = resultSet.getInt("marks");
				int subjectid = resultSet.getInt("subjectid");
				int markid = resultSet.getInt("markid");
				System.out.println("Subject Name : " + subjectname);
				System.out.println("Marks : " + marks);
				studentList.add(new Student(studentid,rollno,studentname,subjectid,subjectname,markid,marks));
			}
			resultSet.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return studentList;
	}
	
	public void addMark(Mark mark){
		try {
			String query = "INSERT INTO mark VALUES (DEFAULT, ?, ?, ?)";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, mark.getStudentid());
			statement.setInt(2, mark.getSubjectid());
			statement.setInt(3, mark.getMarks());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateMark(Student student){
		try {

			//String query = "UPDATE mark SET marks = ? WHERE markid = ? AND studentid = ? AND subjectid = ? LIMIT 1";
			String query = "INSERT INTO mark (markid, studentid, subjectid,marks) VALUES(?,?,?,?) ON DUPLICATE KEY UPDATE studentid=?, subjectid=?, marks = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			
			//statement.setInt(1, student.getMarks());
			//statement.setInt(2, student.getMarkid());
			//statement.setInt(3, student.getStudentid());
			//statement.setInt(4, student.getSubjectid());
			
			statement.setInt(1, student.getMarkid());
			statement.setInt(2, student.getStudentid());
			statement.setInt(3, student.getSubjectid());
			statement.setInt(4, student.getMarks());
			
			statement.setInt(5, student.getStudentid());
			statement.setInt(6, student.getSubjectid());
			statement.setInt(7, student.getMarks());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Schedule> listSchedule()
	{
		List<Schedule> scheduleList = new ArrayList<Schedule>();
         try {
			
			String query = "SELECT * FROM schedule";
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				int scheduleId = resultSet.getInt("scheduleid");
				String subjectName = resultSet.getString("subjectname");
				String timeFrom = resultSet.getString("timefrom");
				String timeTo = resultSet.getString("timeto");
				String day = resultSet.getString("day");
				String className = resultSet.getString("classname");
				
				scheduleList.add(new Schedule(scheduleId, subjectName, timeFrom, timeTo, day, className));
			}
			
			resultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return scheduleList;
    }
	
}
