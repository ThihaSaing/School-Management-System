package admin.classroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import admin.teacher.Teacher;

public class ClassroomDB {

	private Connection connection;

	public ClassroomDB(DataSource dataSource) {

		try {
			this.connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute functions of ClassRoom Data List(Classes,Subjects and Teachers)
	 */
	public List<SubjectClass> getSubjectsByClass() {
		List<SubjectClass> classDataList = new ArrayList<SubjectClass>();
		try {
			String query = "SELECT sc.id,s.subjectid,s.subjectname,c.classid,c.classname FROM subjectclass sc INNER JOIN subject s on sc.subjectid = s.subjectid INNER JOIN class c on sc.classid = c.classid ORDER BY sc.id";
			// String sample = "select sc.id, s.subjectid, s.subjectname, c.classid,
			// c.classname from subject s JOIN subjectclass sc on sc.subjectid = s.subjectid
			// JOIN class c on sc.classid = c.classid order by sc.id";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int subjectid = resultSet.getInt("subjectid");
				String subjectname = resultSet.getString("subjectname");
				int classid = resultSet.getInt("classid");
				String classname = resultSet.getString("classname");
				classDataList.add(new SubjectClass(id, subjectid, classid, subjectname, classname));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classDataList;
	}

	public List<SubjectClassTeacher> getSubjectsByClassAndTeacher() {
		List<SubjectClassTeacher> classDataList = new ArrayList<SubjectClassTeacher>();
		try {
			// String query = "SELECT sc.id,s.subjectid,s.subjectname,c.classid,c.classname
			// FROM subjectclass sc INNER JOIN subject s on sc.subjectid = s.subjectid INNER
			// JOIN class c on sc.classid = c.classid ORDER BY sc.id";
			// String sample = "select sct.id, s.subjectid, s.subjectname, c.classid,
			// c.classname, t.teacherid, t.teachername from subject s JOIN
			// subjectclassteacher sct on sct.subjectid = s.subjectid JOIN class c on
			// sct.classid = c.classid JOIN teacher t on sct.teacherid = t.teacherid order
			// by sct.id";
			String sct = "select sc.id, sc.classid, sc.subjectid, tc.teacherid, s.subjectname, c.classname, t.teachername from subjectclass sc "
					+ " join teacherclass tc on sc.classid = tc.classid "
					+ " join subject s on sc.subjectid = s.subjectid " + " join class c on sc.classid = c.classid "
					+ " join teacher t on tc.teacherid = t.teacherid order by sc.id";
			PreparedStatement statement = connection.prepareStatement(sct);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int subjectid = resultSet.getInt("subjectid");
				String subjectname = resultSet.getString("subjectname");
				int classid = resultSet.getInt("classid");
				String classname = resultSet.getString("classname");
				int teacherid = resultSet.getInt("teacherid");
				String teachername = resultSet.getString("teachername");
				classDataList.add(new SubjectClassTeacher(id, subjectid, classid, teacherid, subjectname, classname,
						teachername));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classDataList;
	}

	public SubjectClass getClassRoomData(int id) {
		SubjectClass subjectclass = null;
		try {
			String query = "SELECT * FROM subjectclass WHERE id = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			int classid = resultSet.getInt("classid");
			int subjectid = resultSet.getInt("subjectid");

			subjectclass = new SubjectClass(id, classid, subjectid);

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectclass;
	}

	/**
	 * Execute functions of Class
	 */
	public List<Class> getClasses() {

		List<Class> classlist = new ArrayList<Class>();
		try {

			String query = "SELECT * FROM Class ORDER BY classid";
			PreparedStatement statement = connection.prepareStatement(query);
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

	public Class getClassDataById(int id) {
		Class classData = null;

		try {

			String query = "SELECT * FROM class WHERE classid = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			int classid = resultSet.getInt("classid");
			String classname = resultSet.getString("classname");
			classData = new Class(classid, classname);

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classData;
	}

	public void addCalss(Class classroom) {
		try {

			String query = "INSERT INTO class VALUES (DEFAULT, ?)";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, classroom.getClassname());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateClass(Class classData) {

		try {

			String query = "UPDATE class SET classname = ? WHERE classid = ? LIMIT 1";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setString(1, classData.getClassname());
			statement.setInt(2, classData.getClassid());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteClass(int classID) {

		try {
			PreparedStatement statement;
			statement = this.connection.prepareCall("{call deleteClass (?)}");

			statement.setInt(1, classID);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Teacher> getTeachers() throws Exception {
		List<Teacher> teachers = new ArrayList<>();

		try {
			String sql = "select * from teacher order by teacherid";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int teacherId = resultSet.getInt("teacherid");
				int subjectId = resultSet.getInt("subjectid");
				int accountId = resultSet.getInt("accountid");
				String teacherName = resultSet.getString("teachername");
				Date teacherDoB = resultSet.getDate("dob");
				String teacherAdd = resultSet.getString("address");
				String teacherPh = resultSet.getString("phoneno");
				String teacherNRIC = resultSet.getString("nric");

				teachers.add(new Teacher(teacherId, subjectId, accountId, teacherName, teacherDoB, teacherAdd,
						teacherPh, teacherNRIC));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teachers;
	}

	/**
	 * Execute functions of Subject
	 */
	public List<Subject> getSubjects() {

		List<Subject> subjectlist = new ArrayList<Subject>();
		try {

			String query = "SELECT * FROM Subject ORDER BY subjectid";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("subjectid");
				String classname = resultSet.getString("subjectname");
				subjectlist.add(new Subject(id, classname));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectlist;
	}

	public Subject getSubjectDataById(int id) {
		Subject subjectData = null;

		try {

			String query = "SELECT * FROM subject WHERE subjectid = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setInt(1, id);

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			int subjectid = resultSet.getInt("subjectid");
			String subjectname = resultSet.getString("subjectname");
			subjectData = new Subject(subjectid, subjectname);

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectData;
	}

	public void addSubject(Subject subject) {
		try {

			String query = "INSERT INTO subject VALUES (DEFAULT, ?)";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, subject.getSubjectname());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateSubject(Subject subject) {

		try {

			String query = "UPDATE Subject SET subjectname = ? WHERE subjectid = ? LIMIT 1";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setString(1, subject.getSubjectname());
			statement.setInt(2, subject.getSubjectid());
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSubject(int subjectID) {

		try {
			PreparedStatement statement;
			statement = this.connection.prepareCall("{call deleteSubject (?)}");

			statement.setInt(1, subjectID);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute functions of AssignSubjectToClass
	 */
	public List<SubjectClass> getAssignSubjectToClass() {
		List<SubjectClass> classDataList = new ArrayList<SubjectClass>();
		try {
			String query = "SELECT sc.id,s.subjectid,s.subjectname,c.classid,c.classname FROM subjectclass sc INNER JOIN subject s on sc.subjectid = s.subjectid INNER JOIN class c on sc.classid = c.classid ORDER BY sc.id";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int subjectid = resultSet.getInt("subjectid");
				String subjectname = resultSet.getString("subjectname");
				int classid = resultSet.getInt("classid");
				String classname = resultSet.getString("classname");
				classDataList.add(new SubjectClass(id, subjectid, classid, subjectname, classname));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classDataList;
	}

	public SubjectClass getAssignSubjectToClassByID(int id) {
		SubjectClass subjectClassData = null;
		try {
			String query = "SELECT sc.id,s.subjectid,s.subjectname,c.classid,c.classname FROM subjectclass sc INNER JOIN subject s on sc.subjectid = s.subjectid INNER JOIN class c on sc.classid = c.classid WHERE sc.id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int subjectid = resultSet.getInt("subjectid");
				String subjectname = resultSet.getString("subjectname");
				int classid = resultSet.getInt("classid");
				String classname = resultSet.getString("classname");
				subjectClassData = new SubjectClass(id, subjectid, classid, subjectname, classname);
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return subjectClassData;
	}

	public void addAssignSubjectToClass(SubjectClass subjectClass) {
		try {

			String query = "INSERT INTO subjectclass VALUES (DEFAULT, ?,?)";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, subjectClass.getSubjectid());
			statement.setInt(2, subjectClass.getClassid());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAssignSubjectToClass(SubjectClass subjectClass) {
		try {
			String query = "UPDATE subjectclass SET classid = ?,subjectid = ? WHERE id = ? LIMIT 1";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, subjectClass.getClassid());
			statement.setInt(2, subjectClass.getSubjectid());
			statement.setInt(3, subjectClass.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAssignSubjectToClass(int id) {

		try {
			String query = "DELETE FROM SUBJECTCLASS WHERE ID = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Execute functions of AssignTeacherToClass
	 */
	public List<TeacherClass> getAssignTeacherToClass() {
		List<TeacherClass> classDataList = new ArrayList<TeacherClass>();
		try {
			String query = "SELECT tc.id,t.teacherid,t.teachername,c.classid,c.classname FROM teacherclass tc "
					+ "INNER JOIN teacher t on tc.teacherid = t.teacherid "
					+ "INNER JOIN class c on tc.classid = c.classid ORDER BY tc.id";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int teacherid = resultSet.getInt("teacherid");
				String teachername = resultSet.getString("teachername");
				int classid = resultSet.getInt("classid");
				String classname = resultSet.getString("classname");
				classDataList.add(new TeacherClass(id, classid, teacherid, teachername, classname));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classDataList;
	}

	public TeacherClass getAssignTeacherToClassByID(int id) {
		TeacherClass teacherClassData = null;
		try {
			String query = "SELECT tc.id,t.teacherid,t.teachername,c.classid,c.classname FROM teacherclass tc INNER JOIN teacher t on tc.teacherid = t.teacherid INNER JOIN class c on tc.classid = c.classid WHERE tc.id = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int teacherid = resultSet.getInt("teacherid");
				String teachername = resultSet.getString("teachername");
				int classid = resultSet.getInt("classid");
				String classname = resultSet.getString("classname");
				teacherClassData = new TeacherClass(id, classid, teacherid, classname, teachername);
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teacherClassData;
	}

	public void addAssignTeacherToClass(TeacherClass teacherClass) {
		try {

			String query = "INSERT INTO teacherclass VALUES (DEFAULT, ?,?)";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, teacherClass.getTeacherid());
			statement.setInt(2, teacherClass.getClassid());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAssignTeacherToClass(TeacherClass teacherClass) {
		try {
			String query = "UPDATE subjectclass SET classid = ?,subjectid = ? WHERE id = ? LIMIT 1";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, teacherClass.getClassid());
			statement.setInt(2, teacherClass.getTeacherid());
			statement.setInt(3, teacherClass.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAssignTeacherToClass(int id) {

		try {
			String query = "DELETE FROM TEACHERCLASS WHERE ID = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setInt(1, id);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addTeacherToClass(TeacherClass teacherClass) {
		try {
			String query = "insert into teacherclass values (DEFAULT, ?, ?)";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, teacherClass.getTeacherid());
			statement.setInt(2, teacherClass.getClassid());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<TeacherClass> getTeacherClassByClassId(int classid) {
		List<TeacherClass> classDataList = new ArrayList<>();
		try {
			String query = "select id, t.teacherid, t.teachername, c.classname, s.subjectname from teacherclass tc "
					+ "left join teacher t on tc.teacherid = t.teacherid "
					+ "left join class c on tc.classid = c.classid "
					+ "left join subject s on t.subjectid = s.subjectid where tc.classid=?";
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, classid);
			ResultSet set = stmt.executeQuery();
			while (set.next()) {
				int id = set.getInt("id");
				int teacherid = set.getInt("teacherid");
				String teachername = set.getString("teachername");
				String classname = set.getString("classname");
				String subjectname = set.getString("subjectname");
				classDataList.add(new TeacherClass(id, classid, teacherid, 0, classname, teachername, subjectname));
			}
			set.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classDataList;
	}

	public List<Teacher> getTeachersBySubjectId(int subjectid) {
		List<Teacher> teachers = new ArrayList<>();

		try {
			String sql = "select * from teacher order by teacherid where subjectid = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, subjectid);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int teacherId = resultSet.getInt("teacherid");
				int subjectId = resultSet.getInt("subjectid");
				int accountId = resultSet.getInt("accountid");
				String teacherName = resultSet.getString("teachername");
				Date teacherDoB = resultSet.getDate("dob");
				String teacherAdd = resultSet.getString("address");
				String teacherPh = resultSet.getString("phoneno");
				String teacherNRIC = resultSet.getString("nric");

				teachers.add(new Teacher(teacherId, subjectId, accountId, teacherName, teacherDoB, teacherAdd,
						teacherPh, teacherNRIC));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teachers;
	}
	
	public void deleteData(int id, int teacherID) {
		
		try {
			
			String query = "UPDATE teacher SET subjectid = null WHERE teacherid = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, teacherID);
			
			statement.executeUpdate();
			
			query = "DELETE FROM teacherclass WHERE id = ?";
			statement = this.connection.prepareStatement(query);
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
