package guest;

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

/**
 * Servlet implementation class IndexController
 */
@WebServlet("/Index")
public class IndexController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       	
	private GuestNewsDB gnewsdb;
	
	@Resource(name="jdbc/school_management")
	private DataSource dataSource;
      
	
	@Override
	public void init() {
		
		try {
			super.init();
			gnewsdb = new GuestNewsDB(dataSource);
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    
	public IndexController() {
        super();
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<GuestNews> guestNews = gnewsdb.loadForIndex();
		
		request.setAttribute("news", guestNews);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
		
}
