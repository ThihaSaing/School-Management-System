package admin.classroom;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import admin.teacher.Teacher;

/**
 * Servlet implementation class ClassroomControlController
 */
@WebServlet("/ClassroomControlController")
public class ClassroomControlController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ClassroomDB classroomDB;

	@Resource(name = "jdbc/school_management")
	private DataSource dataSource;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClassroomControlController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		classroomDB = new ClassroomDB(dataSource);
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			action = "list";
		}

		try {
			switch (action) {
			case "listClassData":
				this.classroomDataList(request, response);
				break;
			case "loadSubject":
				this.loadSubject(request, response);
				break;
			case "loadAssignSubjectToClass":
				this.loadAssignSubjectToClass(request, response);
				break;
			case "loadAssignTeacherToClass":
				this.loadAssignTeacherToClass(request, response);
				break;

			case "addSubject":
				this.addSubject(request, response);
				break;
			case "loadUpdateSubject":
				this.loadUpdateSubject(request, response);
				break;
			case "loadDeleteSubject":
				this.loadDeleteSubject(request, response);
				break;
			case "updateSubject":
				this.updateSubject(request, response);
				break;
			case "deleteSubject":
				this.deleteSubject(request, response);
				break;

			case "addClass":
				this.addClass(request, response);
				break;
			case "addData":
				this.addData(request, response);
				break;
			case "loadTeacherBySubject":
				this.loadTeacherBySubject(request, response);
			case "updateClass":
				this.updateClass(request, response);
				break;
			case "deleteClass":
				this.deleteClass(request, response);
				break;
			case "deleteData":
				this.deleteData(request, response);
			case "loadAddData":
				this.loadAddData(request, response);
				break;
			case "loadUpdateClass":
				this.loadUpdateClass(request, response);
				break;
			case "loadDeleteClass":
				this.loadDeleteClass(request, response);
				break;

			case "addAssignSubjectToClass":
				this.addAssignSubjectToClass(request, response);
				break;
			case "updateAssignSubjectToClass":
				this.updateAssignSubjectToClass(request, response);
				break;
			case "deleteAssignSubjectToClass":
				this.deleteAssignSubjectToClass(request, response);
				break;
			case "loadUpdateAssignSubjectToClass":
				this.loadUpdateAssignSubjectToClass(request, response);
				break;
			case "loadDeleteAssignSubjectToClass":
				this.loadDeleteAssignSubjectToClass(request, response);
				break;

			case "addAssignTeacherToClass":
				this.addAssignTeacherToClass(request, response);
				break;
			case "updateAssignTeacherToClass":
				this.updateAssignTeacherToClass(request, response);
				break;
			case "deleteAssignTeacherToClass":
				this.deleteAssignTeacherToClass(request, response);
				break;

			case "loadUpdateAssignTeacherToClass":
				this.loadUpdateAssignTeacherToClass(request, response);
				break;
			case "loadDeleteAssignTeacherToClass":
				this.loadDeleteAssignTeacherToClass(request, response);
				break;

			default:
				this.loadClass(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * Execute functions of classes,subjects and teachers
	 */
	private void classroomDataList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<SubjectClassTeacher> classDataList = classroomDB.getSubjectsByClassAndTeacher();
		request.setAttribute("classDataList", classDataList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/class-subject.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * Execute functions of Class
	 */
	private void loadClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Class> classList = classroomDB.getClasses();
		request.setAttribute("classList", classList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/classroom-list.jsp");
		dispatcher.forward(request, response);
	}

	private void loadAddData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int classID = Integer.parseInt(request.getParameter("classid"));
		List<TeacherClass> subjectTeacher = classroomDB.getTeacherClassByClassId(classID);
		request.setAttribute("classDataList", subjectTeacher);
		request.setAttribute("classid", classID);
		List<Subject> subjectList = classroomDB.getSubjects();
		request.setAttribute("subjectList", subjectList);
		List<Teacher> teacherList = classroomDB.getTeachers();
		request.setAttribute("teacherList", teacherList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-data-class.jsp");
		dispatcher.forward(request, response);
	}

	private void loadTeacherBySubject(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int classID = Integer.parseInt(request.getParameter("classid"));
		List<TeacherClass> subjectTeacher = classroomDB.getTeacherClassByClassId(classID);
		request.setAttribute("classDataList", subjectTeacher);
		request.setAttribute("classid", classID);
		List<Subject> subjectList = classroomDB.getSubjects();
		request.setAttribute("subjectList", subjectList);
		int subjectid = Integer.parseInt(request.getParameter("subject"));
		List<Teacher> teacherList = classroomDB.getTeachersBySubjectId(subjectid);
		request.setAttribute("teacherList", teacherList);
	}

	private void loadUpdateClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int classID = Integer.parseInt(request.getParameter("classid"));
		Class classroom = classroomDB.getClassDataById(classID);
		request.setAttribute("classData", classroom);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-class.jsp");
		dispatcher.forward(request, response);
	}

	private void loadDeleteClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int classID = Integer.parseInt(request.getParameter("classid"));
		Class classroom = classroomDB.getClassDataById(classID);
		request.setAttribute("classData", classroom);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/delete-class.jsp");
		dispatcher.forward(request, response);
	}

	private void addClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String className = request.getParameter("className");
		Class classroom = new Class(0, className);
		classroomDB.addCalss(classroom);
		this.loadClass(request, response);
	}

	private void addData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int classid = Integer.parseInt(request.getParameter("classID"));
		int teacherid = Integer.parseInt(request.getParameter("teacher"));
		int subjectid = Integer.parseInt(request.getParameter("subject"));
		TeacherClass teacherClass = new TeacherClass(0, classid, teacherid);
		SubjectClass subjectClass = new SubjectClass(0, subjectid, classid);
		classroomDB.addTeacherToClass(teacherClass);
		classroomDB.addAssignSubjectToClass(subjectClass);
		this.loadAssignTeacherToClass(request, response);	
	}

	private void updateClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int classID = Integer.parseInt(request.getParameter("classID"));
		String className = request.getParameter("className");
		Class classdata = new Class(classID, className);
		classroomDB.updateClass(classdata);
		this.loadClass(request, response);
	}

	private void deleteClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int classID = Integer.parseInt(request.getParameter("classID"));
		classroomDB.deleteClass(classID);
		this.loadClass(request, response);
	}

	/**
	 * Execute functions of Subject
	 */
	private void loadSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Subject> subjectList = classroomDB.getSubjects();
		request.setAttribute("subjectList", subjectList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-subject.jsp");
		dispatcher.forward(request, response);
	}

	private void loadUpdateSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int subjectID = Integer.parseInt(request.getParameter("subjectid"));
		Subject subject = classroomDB.getSubjectDataById(subjectID);
		request.setAttribute("subjectData", subject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-subject.jsp");
		dispatcher.forward(request, response);
	}

	private void loadDeleteSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int subjectID = Integer.parseInt(request.getParameter("subjectid"));
		Subject subject = classroomDB.getSubjectDataById(subjectID);
		request.setAttribute("subjectData", subject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/delete-subject.jsp");
		dispatcher.forward(request, response);
	}

	private void addSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String subjectName = request.getParameter("subjectName");
		Subject subject = new Subject(0, subjectName);
		classroomDB.addSubject(subject);
		this.loadSubject(request, response);
	}

	private void updateSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int subjectID = Integer.parseInt(request.getParameter("subjectID"));
		String subjectName = request.getParameter("subjectName");
		Subject subjectdata = new Subject(subjectID, subjectName);
		classroomDB.updateSubject(subjectdata);
		this.loadSubject(request, response);
	}

	private void deleteSubject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int subjectID = Integer.parseInt(request.getParameter("subjectID"));
		classroomDB.deleteSubject(subjectID);
		this.loadSubject(request, response);
	}

	/**
	 * Execute functions of AssignSubjectToClass
	 */
	private void loadAssignSubjectToClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<SubjectClass> assignSubjectToClassList = classroomDB.getAssignSubjectToClass();
		request.setAttribute("classDataList", assignSubjectToClassList);
		List<Class> classList = classroomDB.getClasses();
		request.setAttribute("classList", classList);
		List<Subject> subjectList = classroomDB.getSubjects();
		request.setAttribute("subjectList", subjectList);
		// request.setAttribute("subjectID",assignSubjectToClassList.get(0).getSubjectid()
		// );
		RequestDispatcher dispatcher = request.getRequestDispatcher("/assign-subjects-to-class.jsp");
		dispatcher.forward(request, response);
	}

	private void loadUpdateAssignSubjectToClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		SubjectClass subjectClassDataList = classroomDB.getAssignSubjectToClassByID(id);
		request.setAttribute("subjectClassDataList", subjectClassDataList);
		List<Class> classList = classroomDB.getClasses();
		request.setAttribute("classList", classList);
		List<Subject> subjectList = classroomDB.getSubjects();
		request.setAttribute("subjectList", subjectList);
		request.setAttribute("subjectClassClassid", subjectClassDataList.getClassid());
		request.setAttribute("subjectClassSubjectid", subjectClassDataList.getSubjectid());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-assign-subject-to-class.jsp");
		dispatcher.forward(request, response);
	}

	private void loadDeleteAssignSubjectToClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		SubjectClass subjectClassDataList = classroomDB.getAssignSubjectToClassByID(id);
		request.setAttribute("subjectClassDataList", subjectClassDataList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/delete-assign-subject-to-class.jsp");
		dispatcher.forward(request, response);
	}

	private void addAssignSubjectToClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int classid = Integer.parseInt(request.getParameter("classList"));
		int subjectid = Integer.parseInt(request.getParameter("subjectList"));
		SubjectClass subjectClass = new SubjectClass(0, subjectid, classid);
		classroomDB.addAssignSubjectToClass(subjectClass);
		this.loadAssignSubjectToClass(request, response);
	}

	private void updateAssignSubjectToClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int classid = Integer.parseInt(request.getParameter("classList"));
		int subjectid = Integer.parseInt(request.getParameter("subjectList"));
		SubjectClass subjectClass = new SubjectClass(id, subjectid, classid);
		classroomDB.updateAssignSubjectToClass(subjectClass);
		this.loadAssignSubjectToClass(request, response);
	}

	private void deleteAssignSubjectToClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		classroomDB.deleteAssignSubjectToClass(id);
		this.loadAssignSubjectToClass(request, response);
	}

	/**
	 * Execute functions of AssignTeacherToClass
	 * 
	 * @throws Exception
	 */
	/*
	private void loadAssignTeacherToClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<TeacherClass> assignTeacherToClassList = classroomDB.getAssignTeacherToClass();
		request.setAttribute("classDataList", assignTeacherToClassList);
		List<Class> classList = classroomDB.getClasses();
		request.setAttribute("classList", classList);
		List<Teacher> teacherList = classroomDB.getTeachers();
		request.setAttribute("teacherList", teacherList);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/assign-teachers-to-class.jsp");
		dispatcher.forward(request, response);
	} */
	
	private void loadAssignTeacherToClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Class> classList = classroomDB.getClasses();
		request.setAttribute("classList", classList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/assign-teacher.jsp");
		dispatcher.forward(request, response);
	} 
	

	private void loadUpdateAssignTeacherToClass(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		int id = Integer.parseInt(request.getParameter("id"));
		TeacherClass teacherClassDataList = classroomDB.getAssignTeacherToClassByID(id);
		request.setAttribute("teacherClassDataList", teacherClassDataList);
		List<Class> classList = classroomDB.getClasses();
		request.setAttribute("classList", classList);
		List<Teacher> teacherList = classroomDB.getTeachers();
		request.setAttribute("teacherList", teacherList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-assign-teacher-to-class.jsp");
		dispatcher.forward(request, response);
	}

	private void loadDeleteAssignTeacherToClass(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		TeacherClass teacherClassDataList = classroomDB.getAssignTeacherToClassByID(id);
		request.setAttribute("teacherClassDataList", teacherClassDataList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/delete-assign-teacher-to-class.jsp");
		dispatcher.forward(request, response);
	}

	private void addAssignTeacherToClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int classid = Integer.parseInt(request.getParameter("classList"));
		int teacherid = Integer.parseInt(request.getParameter("teacherList"));
		TeacherClass teacherClass = new TeacherClass(0, teacherid, classid);
		classroomDB.addAssignTeacherToClass(teacherClass);
		this.loadAssignTeacherToClass(request, response);
	}

	private void updateAssignTeacherToClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		int classid = Integer.parseInt(request.getParameter("classList"));
		int teacherid = Integer.parseInt(request.getParameter("teacherList"));
		TeacherClass teacherClass = new TeacherClass(id, classid, teacherid);
		classroomDB.updateAssignTeacherToClass(teacherClass);
		this.loadAssignTeacherToClass(request, response);
	}

	private void deleteAssignTeacherToClass(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		classroomDB.deleteAssignTeacherToClass(id);
		this.loadAssignTeacherToClass(request, response);
	}
	
	private void deleteData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int id = Integer.parseInt(request.getParameter("id"));
		int teacherID = Integer.parseInt(request.getParameter("teacher"));
		classroomDB.deleteData(id, teacherID);
		this.loadAssignTeacherToClass(request, response);
	}
}
