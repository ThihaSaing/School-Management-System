package student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDB {
	
	private Connection link;
	
	public StudentDB(DataSource datasource) throws SQLException {
		
		this.link = datasource.getConnection();	
	}
	
	public Student loadProflie(int studentID) {
		
		Student student = null;
		
		try {
			
			String query = "SELECT * FROM student WHERE studentid = ?";
			PreparedStatement statement = this.link.prepareStatement(query);
			statement.setInt(1, studentID);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			
			int accountid = resultSet.getInt("accountid");
			int classid = resultSet.getInt("classid");
			int rollno = resultSet.getInt("rollno");
			String name = resultSet.getString("studentname");
			String dob = resultSet.getString("dob");
			String address = resultSet.getString("address");
			String phone = resultSet.getString("phoneno");
			String nric = resultSet.getString("nric");
			
			query = "SELECT * FROM class WHERE classid = ?";
			statement = this.link.prepareStatement(query);
			statement.setInt(1, classid);
			
			resultSet = statement.executeQuery();
			resultSet.next();
			
			String className = resultSet.getString("classname");
			
			
			student = new Student(studentID, accountid, classid, rollno, name, dob, address, phone, nric, className);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return student;
	}
	
	public boolean editAccount(int id, String email, String oldPassword, String newPassword) {
		
		boolean state = true;
		
		try {
			
			String query = "SELECT * FROM account WHERE accountid = ? AND password = ?";
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setInt(1, id);
			statement.setString(2, oldPassword);
			
			System.out.println(id);
			System.out.println(oldPassword);
			
			ResultSet resultSet = statement.executeQuery();
			state = resultSet.next();
			
			if (state) {
				
				if(newPassword.equals("")) {
					newPassword = resultSet.getString("password");
				}
				
				query = "UPDATE account SET email = ?, password = ? WHERE accountid = ?";
				
				statement = this.link.prepareStatement(query);
				statement.setString(1, email);
				statement.setString(2, newPassword);
				statement.setInt(3, id);
				
				statement.executeUpdate();
			} 
 			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return state;
	}
	
	public List<Teacher> getTeachers (int classid) {
		
		List<Teacher> teachers = new ArrayList<Teacher>();
		
		try {
			
			String query = "SELECT teachername, subjectname FROM teacher t INNER JOIN teacherclass tc INNER JOIN class c INNER JOIN subject s WHERE t.teacherid = tc.teacherid AND c.classid = tc.classid AND t.subjectid = s.subjectid AND c.classid = ?";
			PreparedStatement statement = this.link.prepareStatement(query);
			statement.setInt(1, classid);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				String teacherName = resultSet.getString("teachername");
				String subjectName = resultSet.getString("subjectname");
				
				teachers.add(new Teacher(subjectName, teacherName));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return teachers;
	}
	
	public List<Student> getStudents (int classid) {
		
		List<Student> students = new ArrayList<Student>();
			
		try {
			
			String query = "SELECT studentname FROM student WHERE classid = ?";
			PreparedStatement statement = this.link.prepareStatement(query);
			statement.setInt(1, classid);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				String name = resultSet.getString("studentname");
				
				students.add(new Student(name));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return students;
	}
	
	public List<Mark> getMarks (int studentid) {
		
		List<Mark> marks = new ArrayList<Mark>();
		
		 try {
			 
			 String query = "SELECT subjectname, marks FROM subject s INNER JOIN mark m WHERE s.subjectid = m.markid AND studentid = ?";
			 PreparedStatement statement = this.link.prepareStatement(query);
			 statement.setInt(1, studentid);
			 
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()) {
				 
				 String subjectname = resultSet.getString("subjectname");
				 int mark = resultSet.getInt("marks");
				 
				 marks.add(new Mark(subjectname, mark));
			 }
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
		 return marks;
	}
	
	public List<Schedule> listSchedule()
	{
		List<Schedule> scheduleList = new ArrayList<Schedule>();
         try {
			
			String query = "SELECT * FROM schedule";
			PreparedStatement statement = this.link.prepareStatement(query);
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
