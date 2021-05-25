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


@WebServlet("/News")
public class GuestNewsControllerServlet extends HttpServlet {
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
    
	public GuestNewsControllerServlet() {
        super();
    }
	
	
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "list";
		}
		
		switch(action) {
 			case "list": this.list(request, response); break;
			case "loadDetail":this.loadDetail(request, response);break;
			case "detail": this.detail(request, response);break;
			default: this.list(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<GuestNews> guestnews = gnewsdb.listNews();
		request.setAttribute("guestnews", guestnews);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("all_news.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void loadDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
        int gnewsID = Integer.parseInt(request.getParameter("gnewsID"));
		
		GuestNews gnews = gnewsdb.loadGuestNews(gnewsID);
		request.setAttribute("guestnews", gnews);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("detail_news.jsp");
		dispatcher.forward(request, response); 

	}
	
   private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
	    int gnewsID = Integer.parseInt(request.getParameter("gnewsID"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		GuestNews gnews=new GuestNews(gnewsID, title, content);
		gnewsdb.detailNews(gnews);
		response.sendRedirect("GuestNewsControllerServlet"); 

	}
	
	

}
