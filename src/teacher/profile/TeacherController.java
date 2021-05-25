package teacher.profile;

import java.io.IOException;

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
 * Servlet implementation class TeacherController
 */
@WebServlet("/TeacherController")
public class TeacherController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private TeacherDbUtil teacherDbUtil;

	@Resource(name = "jdbc/school_management")
	private DataSource dataSource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherController() {
        super();
    }
    
    @Override
    public void init() throws ServletException{  	
    	super.init();   
    	teacherDbUtil = new TeacherDbUtil(dataSource);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");	
		if (action == null) {
			action = "loadProfile";
		}
			
		switch(action) {
			case "loadProfile": this.loadTeacherProfile(request, response); break;
			default: this.loadTeacherProfile(request, response); break;
		}
	}
	
	/**
	 * Execute function of Teacher Profile
	 */
	private void loadTeacherProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		HttpSession session = request.getSession();
		
		int id = (int) session.getAttribute("id");
		
		Teacher profile = teacherDbUtil.getProfile(id);
		request.setAttribute("profile", profile);
		String subjectName = teacherDbUtil.getSubjectNameById(profile.getSubjectId());
		request.setAttribute("subjectName", subjectName);
		String className = teacherDbUtil.getClassNameByTeacherId(id);
		request.setAttribute("className", className);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacher-profile.jsp");
		dispatcher.forward(request, response);
	}
	
	/*private void loadClassInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int id = 1;
		
		List<TeacherClass> teacherClassInfo = teacherDbUtil.getTeacherClassInfoByTeacherId(id);
		request.setAttribute("teacherClassInfo", teacherClassInfo);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacher-classinfo.jsp");
		dispatcher.forward(request, response);
	}
	*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
