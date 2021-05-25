package teacher.classInfo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class TeacherClassInfoController
 */
@WebServlet("/TeacherClassInfoController")
public class TeacherClassInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherClassInfoDbUtil classInfoDbUtil;

	@Resource(name = "jdbc/school_management")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherClassInfoController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException{  	
    	super.init();   
    	classInfoDbUtil = new TeacherClassInfoDbUtil(dataSource);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");	
		if (action == null) {
			action = "loadClassInfo";
		}
			
		switch(action) {
			case "loadClassInfo": this.loadClassInfo(request, response); break;
			case "loadStudentList": this.loadStudentList(request, response); break;
			case "loadAddMarks": try {
				this.loadAddMarks(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} break;
			case "addMark" : try {
				this.addMark(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}break;
			case "updateMark": try {
				this.updateMark(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} break;
			default: this.loadClassInfo(request, response); break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void loadClassInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		
		List<TeacherClass> teacherClassDataList = classInfoDbUtil.getTeacherClassInfoByTeacherId(id);
		List<Schedule> schedule = classInfoDbUtil.listSchedule();
		request.setAttribute("schedule", schedule);
		request.setAttribute("teacherClassDataList", teacherClassDataList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacher-classinfo.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadStudentList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int classid = Integer.parseInt(request.getParameter("classid"));
		List<Student> student = classInfoDbUtil.getStudentListByClassId(classid);
		request.setAttribute("studentList", student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacher-student-list.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadAddMarks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		int teacherId = 1;
		int classid = Integer.parseInt(request.getParameter("classid"));
		int studentid = Integer.parseInt(request.getParameter("studentid"));
		List<Student> student = classInfoDbUtil.getMarks(studentid,classid);
		request.setAttribute("studentMarks", student);
		String studentName = classInfoDbUtil.getStudentNamebyStudentID(studentid);
		request.setAttribute("studentName", studentName);
		int teacherSubjectId = classInfoDbUtil.getSubjectIdByTeacherId(teacherId);
		request.setAttribute("teacherSubjectId", teacherSubjectId);
		int teacherMarkId = classInfoDbUtil.getMarkIdBySubjectIdAndStudentId(studentid,teacherSubjectId);
		request.setAttribute("teacherMarkId", teacherMarkId);
		
		request.setAttribute("classid", classid);
		request.setAttribute("studentid", studentid);
		request.setAttribute("subjectid", teacherSubjectId);
		String subjectName = classInfoDbUtil.getSubjectNameBySubjectId(teacherSubjectId);
		request.setAttribute("subjectname", subjectName);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacher-update-marks.jsp");
		dispatcher.forward(request, response);
	}
	
	private void updateMark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		int teacherSubjectID = Integer.parseInt(request.getParameter("teacherSubjectID"));
		int teacherMarkID = Integer.parseInt(request.getParameter("teacherMarkID"));
		int teacherStudentID =  Integer.parseInt(request.getParameter("studentid"));
		int marks = Integer.parseInt(request.getParameter("enableMarks"));
		classInfoDbUtil.updateMark(new Student(teacherStudentID, teacherSubjectID, teacherMarkID, marks));
		this.loadAddMarks(request, response);
		
	}
	
	private void addMark(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
		int subjectId = Integer.parseInt(request.getParameter("subjectId"));
		int studentid = Integer.parseInt(request.getParameter("studentid"));
		int marks = Integer.parseInt(request.getParameter("enableMarks"));
		classInfoDbUtil.addMark(new Mark(0,studentid,subjectId,marks));
		this.loadAddMarks(request, response);
	}
}
