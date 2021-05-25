package admin.schedule;

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
import javax.sql.DataSource;

@WebServlet("/ScheduleControllerServlet")
public class ScheduleControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ScheduleDB scheduledb;
	
	@Resource(name="jdbc/school_management")
	private DataSource dataSource;
       
	@Override
	public void init() {
		
		try {
			super.init();
			scheduledb = new ScheduleDB(dataSource);
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
    public ScheduleControllerServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "list";
		}
		
		switch(action) {
 			case "list": this.list(request, response); break;
 			case "loadAdd": this.loadAdd(request, response); break;
			case "add":this.add(request, response);break;
			case "loadUpdate": this.loadUpdate(request, response); break;
			case "update": this.update(request, response);break;
			case "loadDelete": this.loadDelete(request, response); break;
			case "delete": this.delete(request, response); break;
			default: this.list(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private void loadAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("add_schedule.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String subjectName = request.getParameter("subject");
		String timeFrom = request.getParameter("timefrom");
		String timeTo = request.getParameter("timeto");
		String day = request.getParameter("day");
		String className = request.getParameter("class");
		Schedule theSchedule=new Schedule(0, subjectName, timeFrom, timeTo, day, className);
		scheduledb.addSchedule(theSchedule);
		response.sendRedirect("ScheduleControllerServlet");	
		
	}
	
	private void loadUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int scheduleId = Integer.parseInt(request.getParameter("scheduleid"));
		
		Schedule schedule = scheduledb.loadSchedule(scheduleId);
		request.setAttribute("schedule", schedule);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("update_schedule.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int scheduleId = Integer.parseInt(request.getParameter("scheduleid"));
		String subjectName = request.getParameter("subject");
		String timeFrom = request.getParameter("timefrom");
		String timeTo = request.getParameter("timeto");
		String day = request.getParameter("day");
		String className = request.getParameter("class");
		Schedule theSchedule=new Schedule(scheduleId, subjectName, timeFrom, timeTo, day, className);
		scheduledb.updateSchedule(theSchedule);
		response.sendRedirect("ScheduleControllerServlet");
		
	}
	
	private void loadDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int scheduleId = Integer.parseInt(request.getParameter("scheduleid"));
		
		Schedule schedule = scheduledb.loadSchedule(scheduleId);
		
		request.setAttribute("schedule", schedule);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("delete_schedule.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

		int scheduleId = Integer.parseInt(request.getParameter("scheduleid"));
		
		scheduledb.deleteSchedule(scheduleId);
		
		response.sendRedirect("ScheduleControllerServlet");
		
	}

	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Schedule> scheduleList = scheduledb.listSchedule();
		request.setAttribute("schedule", scheduleList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list_schedule.jsp");
		dispatcher.forward(request, response);
		
	}

	

}
