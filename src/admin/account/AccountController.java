package admin.account;

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

@WebServlet("/AccountController")
public class AccountController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private AccountDB accountDB;
	
	@Resource(name="jdbc/school_management")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		
		try {
			
			super.init();
			accountDB = new AccountDB(dataSource);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

    public AccountController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if (action == null) {
			action = "logout";
		}
		
		switch(action) {
			case "login": this.login(request, response); break;
			case "create": this.create(request, response); break;
			case "list": this.list(request, response); break;
			case "confirmDelete": this.confirmDelete(request, response); break;
			case "delete": this.delete(request, response); break;
			case "change": this.changePassword(request, response); break;
			default: this.logout(request, response); break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Account account = accountDB.checkLogin(email, password);
		
		if (account != null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("account", account.getName());
			session.setAttribute("email", account.getEmail());
			session.setAttribute("role", account.getRole());
			session.setAttribute("id", account.getRoleid());
			session.setAttribute("accountid", account.getId());
			
			response.sendRedirect("Index");
			
		} else {
			request.setAttribute("message", "Wrong email or password");
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String role = request.getParameter("role");
		int id = Integer.parseInt(request.getParameter("id"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Account account = new Account(0, email, password, role);
		
		int status = accountDB.create(account, id);
		
		if (status != 1) {
			String message = (status == 2) ? "Email already exists" : "ID Not Found";
			
			if (status == 2) {
				message = "Email already exists";
			} else if (status == 3) {
				message = "ID Not Found";
			} else {
				message = "Account already created";
			}
			
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher("createAccount.jsp");
			dispatcher.forward(request, response);
			
		} else {
			this.list(request, response);
		}
		
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Account> accountList = accountDB.getAccounts();
		
		request.setAttribute("accountList", accountList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("accountList.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void confirmDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		Account account = accountDB.getAccount(accountID);
		
		request.setAttribute("account", account);
		RequestDispatcher dispatcher = request.getRequestDispatcher("deleteAccount.jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int accountID = Integer.parseInt(request.getParameter("accountID"));
		int roleID = Integer.parseInt(request.getParameter("roleID"));
		String role = request.getParameter("role");
		
		accountDB.delete(accountID, roleID, role);
		
		this.list(request, response);
	}
	
	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("password");
		String confirmPassword = request.getParameter("password1");
		String message = null;
		
		if(newPassword.equals(confirmPassword)) {
			
			boolean result = accountDB.changePassword(oldPassword, newPassword);
			
			if(!result) {
				message = "Wrong password";
			} else {
				message = "Password changed";
			}
			
		} else {
			message = "The passwords does not match";
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("changePassword.jsp");
		dispatcher.forward(request, response);
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("logout.jsp");
	}
	
	
	

}
