package admin.staff;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/StaffControlController")

public class StaffControlController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private StaffDB staffDB;

	@Resource(name="jdbc/school_management")
	private DataSource dataSource;

	public StaffControlController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		
		try {
			super.init();
			staffDB = new StaffDB(dataSource);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			action = "list";
		}

		switch (action) {
		case "list":
			this.staffDB(request, response);
			break;
		case "loadAdd":
			this.loadAdd(request, response);
			break;
		case "loadAddStaff":
			try {
				this.loadAddStaff(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "loadUpdate":
			try {
				this.loadUpdate(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "update":
			try {
				this.update(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "loadDelete":
			try {
				this.loadDelete(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			break;
		case "delete":
			this.delete(request, response);
			break;
		default:
			this.staffDB(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void staffDB(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Staff> staffDataList = staffDB.getStaffs();
		request.setAttribute("staffDataList", staffDataList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/staff.jsp");
		dispatcher.forward(request, response);
	}

	private void loadAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/add-staff.jsp");
		dispatcher.forward(request, response);
	}

	private void loadAddStaff(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		String staffname = request.getParameter("staffName");
		String position = request.getParameter("position");
		int salary = Integer.parseInt(request.getParameter("salary"));
		String phnumber = request.getParameter("phno");
		String address = request.getParameter("address");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateofBirth = format.parse(request.getParameter("dob"));
		java.sql.Date dob = new java.sql.Date(dateofBirth.getTime());
		// Date dob = Date.parse((request.getParameter("dob"));
		String nrc = request.getParameter("nric");

		Staff staff = new Staff(0, staffname, position, salary, phnumber, address, dob, nrc);

		// StaffDB sdb = new StaffDB(dataSource);
		// sdb.addStaff(staff);
		staffDB.addStaff(staff);
		// response.sendRedirect("StaffControlController");

		this.staffDB(request, response);
		// RequestDispatcher dispatcher =
		// request.getRequestDispatcher("/add-staff.jsp");
		// dispatcher.forward(request, response);
	}

	private void loadUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException, IOException {
		int staffID = Integer.parseInt(request.getParameter("staffID"));

		Staff staff = staffDB.loadStaff(staffID);

		request.setAttribute("staff", staff);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-staff.jsp");
		dispatcher.forward(request, response);
	}

	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException, IOException {
		int staffID = Integer.parseInt(request.getParameter("staffID"));

		String staffname = request.getParameter("staffName");
		String position = request.getParameter("position");
		int salary = Integer.parseInt(request.getParameter("salary"));
		String phnumber = request.getParameter("phno");
		String address = request.getParameter("address");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date dateofBirth = format.parse(request.getParameter("dob"));
		java.sql.Date dob = new java.sql.Date(dateofBirth.getTime());
		String nrc = request.getParameter("nric");

		Staff staff = new Staff(staffID, staffname, position, salary, phnumber, address, dob, nrc);
		staffDB.updateBook(staff);

		this.staffDB(request, response);
		// response.sendRedirect("BookController");
	}

	private void loadDelete(HttpServletRequest request, HttpServletResponse response)
			throws ParseException, ServletException, IOException {
		int staffID = Integer.parseInt(request.getParameter("staffID"));

		Staff staff = staffDB.loadStaff(staffID);

		request.setAttribute("staff", staff);

		RequestDispatcher dispatcher = request.getRequestDispatcher("delete-staff.jsp");
		dispatcher.forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int staffID = Integer.parseInt(request.getParameter("staffID"));

		staffDB.deleteStaff(staffID);

		response.sendRedirect("StaffControlController");
	}
}
