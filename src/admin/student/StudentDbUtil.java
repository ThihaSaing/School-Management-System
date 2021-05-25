package admin.student;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil 
{
	private DataSource dataSource;
	public StudentDbUtil(DataSource theDataSource)
	{
		dataSource=theDataSource;
	}
	public List<Student> getStudents() throws Exception
	{
		List<Student> students=new ArrayList<>();
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs=null;
		
		try
		{
			myConn=dataSource.getConnection();
			String sql="select studentid,accountid,student.classid,classname,rollno,studentname,dob,address,phoneno,nric from student left join class on student.classid = class.classid order by rollno";
			myStmt=myConn.createStatement();
			myRs=myStmt.executeQuery(sql);
			while(myRs.next())
			{
				int studentid=myRs.getInt("studentid");
				int accountid=myRs.getInt("accountid");
				int classid=myRs.getInt("classid");
				int rollno=myRs.getInt("rollno");
				String studentname=myRs.getString("studentname");
				String dob=myRs.getString("dob");
				String address=myRs.getString("address");
				String phoneno=myRs.getString("phoneno");
				String nric=myRs.getString("nric");
				String classname=myRs.getString("classname");
			
				Student tempStudent=new Student(classname,studentid,classid,accountid,studentname,dob,nric,phoneno,address,rollno);
				students.add(tempStudent);
			}
			return students;
		}
		finally
		{
			close(myConn, myStmt, myRs);
		}
		
	}
	
	
	public Student getStudent(String theStudentId) throws Exception
	{
		Student theStudent=null;
		 
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs=null;
		int studentId;
		
		try
		{
			studentId=Integer.parseInt(theStudentId);
			myConn=dataSource.getConnection();
			String sql="select * from student where studentid=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setInt(1,studentId);
			myRs=myStmt.executeQuery();
			
			if(myRs.next())
			{
				int studentid=myRs.getInt("studentid");
				int classid=myRs.getInt("classid");
				int accountid=myRs.getInt("accountid");
				int rollno=myRs.getInt("rollno");
				String studentname=myRs.getString("studentname");
				String dob=myRs.getString("dob");
				String phoneno=myRs.getString("phoneno");
				String address=myRs.getString("address");
				String nric=myRs.getString("nric");
				
				theStudent=new Student(studentid,classid,accountid,studentname,dob,nric,phoneno,address,rollno);
			}
			else
			{
				throw new Exception("Could not find student id: "+studentId);
			}
			return theStudent;
		}
		finally
		{
			close(myConn,myStmt,myRs);
		}
	}
	
	
	
	public void updateStudent(Student theStudent) throws Exception
	{
		Connection myConn=null;
		PreparedStatement myStmt=null;
		System.out.println("Update Student");
		try
		{
			myConn=dataSource.getConnection();
			String sql="update student "
						+"set rollno=?,classid=?,studentname=?,dob=?,address=?,phoneno=?,nric=?"
						+"where studentid=?";
			myStmt=myConn.prepareStatement(sql);
			
			myStmt.setInt(1, theStudent.getRollno());
			myStmt.setInt(2, theStudent.getClassid());
			myStmt.setString(3,theStudent.getStudentname());
			myStmt.setString(4, theStudent.getdob());
			myStmt.setString(5, theStudent.getAddress());
			myStmt.setString(6, theStudent.getPhoneno());
			myStmt.setString(7, theStudent.getNric());
			myStmt.setInt(8, theStudent.getStudentid());
		
			myStmt.execute();
		}
		finally
		{
			close(myConn,myStmt,null);
		}
	}
	
	public void deleteStudent(String theStudentId) throws Exception
	{
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
		try
		{
			int studentId=Integer.parseInt(theStudentId);
			myConn=dataSource.getConnection();
			
			String sql="delete from student where studentid=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setInt(1, studentId);
			myStmt.execute();
		}
		finally
		{
			close(myConn,myStmt,null);
	}
	}
	
	public void addStudent(Student theStudent)
	{
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
		try
		{
			myConn=dataSource.getConnection();
			String sql="insert into student"
					+"(studentid,classid,accountid,studentname,dob,nric,phoneno,address,rollno)"
					+"values(DEFAULT,?,NULL,?,?,?,?,?,?)";
			myStmt=myConn.prepareStatement(sql);
			
			myStmt.setInt(1, theStudent.getClassid());
			myStmt.setString(2,theStudent.getStudentname());
			myStmt.setString(3, theStudent.getdob());
			myStmt.setString(4, theStudent.getNric());
			myStmt.setString(5, theStudent.getPhoneno());
			myStmt.setString(6, theStudent.getAddress());
			myStmt.setInt(7, theStudent.getRollno());
			
			myStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			close(myConn,myStmt,null);
		}
	}
	
	private void close(Connection myConn,Statement myStmt,ResultSet myRs)
	{
		try
		{
			if(myRs!=null)
			{
				myRs.close();
			}
			if(myStmt!=null)
			{
				myStmt.close();
			}
			if(myConn!=null)
			{
				myConn.close();
			}
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
		}
	}
	
	public List<Class> getClasses() {

		List<Class> classlist = new ArrayList<Class>();
		try {
			
			Connection myConn=dataSource.getConnection();

			String query = "SELECT * FROM Class ORDER BY classid";
			PreparedStatement statement = myConn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("classid");
				String classname = resultSet.getString("classname");
				classlist.add(new Class(id, classname));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classlist;
	}
}