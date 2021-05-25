package admin.teacher;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


/**
 * Servlet implementation class TeacherController
 */
@WebServlet("/TeacherControllerServlet")
public class TeacherControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TeacherDbUtil teacherDbUtil;

	@Resource(name = "jdbc/school_management")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		super.init();

		try {
			teacherDbUtil = new TeacherDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	public TeacherControllerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String theCommand = request.getParameter("command");

			if (theCommand == null) {
				theCommand = "LIST";
			}
			switch (theCommand) {
			case "LIST":
				listTeachers(request, response);
				break;

			case "LOADS":
				loadSubjects(request, response);
				break;

			case "ADD":
				addTeacher(request, response);
				break;

			case "LOAD":
				loadTeacher(request, response);
				break;

			case "UPDATE":
				updateTeacher(request, response);
				break;

			case "DELETE":
				deleteTeacher(request, response);
				break;

			default:
				listTeachers(request, response);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void deleteTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String theTeacherId = request.getParameter("teacherId");

		teacherDbUtil.deleteTeacher(theTeacherId);

		listTeachers(request, response);
	}

	public Date formatDate(String stringDate) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
		java.util.Date utilDate = format.parse(stringDate);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		return sqlDate;

	}

	private void updateTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int id = Integer.parseInt(request.getParameter("teacherId"));
		int subjectid = Integer.parseInt(request.getParameter("subject"));
		String teacherName = request.getParameter("teacherName");
		Date teacherDoB = formatDate(request.getParameter("teacherDoB"));
		String address = request.getParameter("teacherAddress");
		String phoneno = request.getParameter("teacherPh");
		String nric = request.getParameter("teacherNRIC");

		Teacher theTeacher = new Teacher(id, teacherName, teacherDoB, address, phoneno, nric);

		teacherDbUtil.updateTeacher(theTeacher, subjectid);

		listTeachers(request, response);
	}

	private void loadTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Subject> subjectList = teacherDbUtil.getSubjects();
		request.setAttribute("subjectList", subjectList);
		String theTeacherId = request.getParameter("teacherId");
		Teacher theTeacher = teacherDbUtil.getTeacher(theTeacherId);
		request.setAttribute("the_teacher", theTeacher);
		request.setAttribute("subjectidFromTeacher", theTeacher.getSubjectId());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_update_teacher.jsp");
		dispatcher.forward(request, response);
	}

	private void addTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int subjectid = Integer.parseInt(request.getParameter("subject"));
		String teacherName = request.getParameter("teacherName");
		Date teacherDoB = formatDate(request.getParameter("teacherDoB"));
		String address = request.getParameter("teacherAddress");
		String phoneno = request.getParameter("teacherPh");
		String nric = request.getParameter("teacherNRIC");
		Teacher theTeacher = new Teacher(0, subjectid, 0, teacherName, teacherDoB, address, phoneno, nric);
		teacherDbUtil.addTeacher(theTeacher);
		listTeachers(request, response);
	}

	private void loadSubjects(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Subject> subjectList = teacherDbUtil.getSubjects();
		request.setAttribute("subjectList", subjectList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_add_teacher.jsp");
		dispatcher.forward(request, response);
	}

	private void listTeachers(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Teacher> teachers = teacherDbUtil.getTeachers();
		request.setAttribute("teachers", teachers);
		List<Subject> subjectList = teacherDbUtil.getSubjects();
		request.setAttribute("subjectList", subjectList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin_teacher_list.jsp");
		dispatcher.forward(request, response);
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

}
