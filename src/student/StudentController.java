package student;

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

@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private StudentDB studentDB;
	
	@Resource(name="jdbc/school_management")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		
		try {
			super.init();
			studentDB = new StudentDB(dataSource);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    public StudentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "profile";
		}
		
		switch(action) {
			case "profile": this.loadProfile(request, response); break;
			case "edit": this.editAccount(request, response); break;
			case "classInfo": this.loadClassInfo(request, response); break;
			case "results": this.loadResults(request, response); break;
			default: this.loadProfile(request, response); break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void loadProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int studentID = (int) session.getAttribute("id");
		
		Student student = studentDB.loadProflie(studentID);
		
		request.setAttribute("student", student);
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentProfile.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void editAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int id = (int) session.getAttribute("accountid");
		String email = request.getParameter("email");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("password");
		String confirmPassword = request.getParameter("password1");
		
		String message = null;
		
		if(newPassword.equals(confirmPassword)) {
			
			boolean result = studentDB.editAccount(id, email, oldPassword, newPassword);
			
			if(!result) {
				message = "Wrong password";
			} else {
				message = "Info Edited";
			}
			
		} else {
			message = "The passwords does not match";
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("editAccount.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadClassInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int studentID = (int) session.getAttribute("id");
		
		Student student = studentDB.loadProflie(studentID);
		List<Teacher> teachers = studentDB.getTeachers(student.getClassid());
		List<Student> students = studentDB.getStudents(student.getClassid());
		List<Schedule> schedule = studentDB.listSchedule();
		
		request.setAttribute("student", student);
		request.setAttribute("teachers", teachers);
		request.setAttribute("students", students);
		request.setAttribute("schedule", schedule);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentClass.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void loadResults(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int studentID = (int) session.getAttribute("id");
		
		List<Mark> marks = studentDB.getMarks(studentID);
		
		request.setAttribute("marks", marks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("studentResults.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	

}
 