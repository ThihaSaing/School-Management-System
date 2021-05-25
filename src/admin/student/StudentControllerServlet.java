package admin.student;

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

@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private StudentDbUtil studentDbUtil;
	
	@Resource(name="jdbc/school_management")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		try
		{
			studentDbUtil=new StudentDbUtil(dataSource);
		}
		catch(Exception exc)
		{
		throw new ServletException(exc);
		}
	}
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			String theCommand=request.getParameter("command");
			
			if(theCommand==null)
			{
				theCommand="LIST";
			}
			switch (theCommand) 
			{
			
			case "LIST":
				listStudents(request, response);
				break;
			
			case "LOADADD":
				loadAddStudent(request, response);
				break;
				
			case "ADD":
				addStudent(request, response);
				break;
				
			case "LOAD":
				loadStudent(request, response);
				break;
				
			case "UPDATE":
				updateStudent(request, response);
				break;
			
			case "DELETE":
				deleteStudent(request, response);
				break;
				
			default:
				listStudents(request, response);
			}
		}
		catch (Exception exc)
		{
			throw new ServletException(exc);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void listStudents(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		List<Student> students=studentDbUtil.getStudents();
		request.setAttribute("STUDENT_LIST",students);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/for_student.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadAddStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Class> classes = studentDbUtil.getClasses();
		request.setAttribute("CLASS_LIST", classes);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/add_student_form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void loadStudent(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String theStudentId=request.getParameter("studentId");
		
		List<Class> classList=studentDbUtil.getClasses();
		request.setAttribute("classList",classList);
		
		Student theStudent=studentDbUtil.getStudent(theStudentId);
		request.setAttribute("studentClassid",theStudent.getClassid());
		
		request.setAttribute("THE_STUDENT",theStudent);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/update_student_form.jsp");
		dispatcher.forward(request,response);
	}
	
	private void deleteStudent(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String theStudentId=request.getParameter("studentId");
		studentDbUtil.deleteStudent(theStudentId);
		listStudents(request, response);
	}
	
	private void updateStudent(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		int studentid=Integer.parseInt(request.getParameter("studentid"));
		int classid = Integer.parseInt(request.getParameter("classid"));
		String studentname=request.getParameter("studentname");
		String dob=request.getParameter("dob");
		String nric=request.getParameter("nric");
		String phoneno=request.getParameter("phoneno");
		String address=request.getParameter("address");
		int rollno=Integer.parseInt(request.getParameter("rollno"));
		Student theStudent=new Student(studentid, classid, 0,studentname,dob,nric,phoneno,address,rollno);
		
		studentDbUtil.updateStudent(theStudent);
		listStudents(request, response);
	}
	
	private void addStudent(HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String studentname=request.getParameter("studentname");
		String dob=request.getParameter("dob");
		String nric=request.getParameter("nric");
		String phoneno=request.getParameter("phoneno");
		String address=request.getParameter("address");
		int rollno=Integer.parseInt(request.getParameter("rollno"));
		int classid = Integer.parseInt(request.getParameter("classid"));
		
		Student theStudent=new Student(0,classid,0,studentname,dob,nric,phoneno,address,rollno);
		studentDbUtil.addStudent(theStudent);
		listStudents(request, response);
		
	}
}