
package admin.news;

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

@WebServlet("/NewsControllerServlet")
public class NewsControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private NewsDB newsdb;
	
	@Resource(name="jdbc/school_management")
	private DataSource dataSource;
      
	
	@Override
	public void init() {
		
		try {
			super.init();
			newsdb = new NewsDB(dataSource);
			
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
    
	public NewsControllerServlet() {
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
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<News> news = newsdb.listNews();
		request.setAttribute("news", news);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("list_news.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void loadAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("add_news.jsp");
		dispatcher.forward(request, response);
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		News news=new News(0, title, content);
		newsdb.addNews(news);
		response.sendRedirect("NewsControllerServlet");		
	}
	
	private void loadUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int newsID = Integer.parseInt(request.getParameter("newsID"));
		
		News news = newsdb.loadNews(newsID);
		request.setAttribute("news", news);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("update_news.jsp");
		dispatcher.forward(request, response);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		
		int newsID = Integer.parseInt(request.getParameter("newsID"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		News news=new News(newsID, title, content);
		newsdb.updateNews(news);
		response.sendRedirect("NewsControllerServlet");

	}
	
	private void loadDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int newsID = Integer.parseInt(request.getParameter("newsID"));
		
		News news = newsdb.loadNews(newsID);
		
		request.setAttribute("news", news);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("delete_news.jsp");
		dispatcher.forward(request, response);
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int newsID = Integer.parseInt(request.getParameter("newsID"));
		
		newsdb.deleteNews(newsID);
		
		response.sendRedirect("NewsControllerServlet");
		
	}
}
