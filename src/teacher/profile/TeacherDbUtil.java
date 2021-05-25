package teacher.profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


import javax.sql.DataSource;

public class TeacherDbUtil {
	
	private Connection connection;
	
	public TeacherDbUtil(DataSource dataSource) {

		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Teacher getProfile(int id){
		Teacher teacher = null;
		try {
			String query = "SELECT * FROM teacher WHERE teacherid = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int accountid = resultSet.getInt("accountid");
			int subjectid = resultSet.getInt("subjectid");
			String teachername = resultSet.getString("teachername");
			Date dob = resultSet.getDate("dob");
			String address = resultSet.getString("address");
			String phoneno = resultSet.getString("phoneno");
			String nric = resultSet.getString("nric");

			teacher = new Teacher(id, subjectid,accountid,teachername,dob,address,phoneno,nric);

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacher;
	}
	
	public String getClassNameByTeacherId(int teacherid){
		String className = null;
		try {
			String query = "select c.classname,t.teachername from class c inner join teacherclass tc on tc.classid = c.classid inner join teacher t on tc.teacherid = t.teacherid where t.teacherid = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, teacherid);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			className = resultSet.getString("classname");
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return className;
	}
	
	public String getSubjectNameById(int subjectID){
		String subjectName = null;
		String query = "SELECT * FROM subject WHERE subjectid = ?";
		PreparedStatement statement;
		try {
			statement = this.connection.prepareStatement(query);
			statement.setInt(1, subjectID);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();	
			subjectName = resultSet.getString("subjectname");
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subjectName;
	}
	
	/*public List<TeacherClass> getTeacherClassInfoByTeacherId(int teacherid){
		List<TeacherClass> teacherClassDataList = new ArrayList<TeacherClass>();
		try {
			String query = "Select tc.id,t.teacherid,t.teachername,c.classid,c.classname,s.subjectid,s.subjectname from teacherclass tc inner join class c on tc.classid = c.classid inner join teacher t on t.teacherid = tc.teacherid inner join subject s on s.subjectid = t.subjectid";
			PreparedStatement statement = connection.prepareStatement(query);
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
	*/
	

}
