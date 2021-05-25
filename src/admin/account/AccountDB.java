package admin.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class AccountDB {

	private Connection link;
	
	public AccountDB(DataSource dataSource) throws SQLException {
		
		this.link = dataSource.getConnection();
	}
	
	public Account checkLogin(String email, String password) {
		
		Account account = null;
		
		try {
			
			String query = "SELECT * FROM account WHERE email = ? AND password = ?";
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setString(1, email);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				
				int id = resultSet.getInt("accountid");
				String role = resultSet.getString("role");
				String name;
				int roleID;

				if (role.equals("admin")) {
					
					name = "admin";
					roleID = 1;
					
				} else {
					
					if (role.equals("student")) {
						query = "SELECT studentid, studentname FROM student WHERE accountid = ?";
					} else {
						query = "SELECT teacherid, teachername FROM teacher WHERE accountid = ?";
					} 
					
					statement = this.link.prepareStatement(query);
					statement.setInt(1, id);
					
					resultSet = statement.executeQuery();
					resultSet.next();
					
					roleID = resultSet.getInt(1);
					name = resultSet.getString(2);
				}
				
				account = new Account(id, email, password, role, roleID, name);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
		
	}
	
	public int create(Account account, int id) {
		
		int status = 1;
		
		try {
			
			String query = "SELECT * FROM account WHERE email = ?";
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setString(1, account.getEmail());
			
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				status = 2;
			} else {
				
				String role = account.getRole();
				
				if (role.equals("student")) {
					query = "SELECT * FROM student WHERE studentid = ?";
				} else {
					query = "SELECT * FROM teacher WHERE teacherid = ?";
				}
				
				statement = this.link.prepareStatement(query);
				
				statement.setInt(1, id);
				
				resultSet = statement.executeQuery();
				
				if(!resultSet.next()) {
					status = 3;
				} else {
					
					int account_id = resultSet.getInt("accountid");
					
					if (account_id > 0) {
						status = 4;
					} else {
					
						query = "INSERT INTO account VALUES (DEFAULT, ? , ? ,?)";
						statement = this.link.prepareStatement(query);
						
						statement.setString(1, account.getEmail());
						statement.setString(2, account.getPassword());
						statement.setString(3, account.getRole());
						
						statement.executeUpdate();
						
						query = "SELECT LAST_INSERT_ID()";
						statement = this.link.prepareStatement(query);
						resultSet = statement.executeQuery();
						
						resultSet.next();
						
						account_id = resultSet.getInt(1);
						
						if (role.equals("student")) {
							query = "UPDATE student SET accountid = ? WHERE studentid = ?";
						} else {
							query = "UPDATE teacher SET accountid = ? WHERE teacherid = ?";
						}
						
						statement = this.link.prepareStatement(query);
						
						statement.setInt(1, account_id);
						statement.setInt(2, id);
						
						statement.executeUpdate();
					}			
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public List<Account> getAccounts() {
		
		List<Account> accounts = new ArrayList<Account>();
		
		try {
			
			String query = "SELECT a.accountID, email, password, role, teacherid AS roleid, teachername  FROM account a INNER JOIN teacher t WHERE a.accountid = t.accountid"; 
			PreparedStatement statement = this.link.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				int id = resultSet.getInt("accountid");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				String role = resultSet.getString("role");
				
				int roleid = resultSet.getInt("roleid");
				String name = resultSet.getString("teachername");
				
				accounts.add(new Account(id, email, password, role, roleid, name));
			}
			
			query = "SELECT a.accountID, email, password, role, studentid AS roleid, studentname  FROM account a INNER JOIN student s WHERE a.accountid = s.accountid"; 
			statement = this.link.prepareStatement(query);
			
			resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				int id = resultSet.getInt("accountid");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				String role = resultSet.getString("role");
				
				int roleid = resultSet.getInt("roleid");
				String name = resultSet.getString("studentname");
				
				accounts.add(new Account(id, email, password, role, roleid, name));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return accounts;
	}
	
	public Account getAccount(int accountID) {
		
		Account account = null;
		
		try {
			
			String query = "SELECT * FROM account WHERE accountid = ?";
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setInt(1, accountID);
			
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				
				int id = resultSet.getInt("accountid");
				String email = resultSet.getString("email");
				String password = resultSet.getString("password");
				String role = resultSet.getString("role");
				
				if(role.equals("student")) {
					query = "SELECT studentid AS roleid, studentname AS name FROM student WHERE accountid = ?";
				} else {
					query = "SELECT teacherid AS roleid, teachername AS name FROM teacher WHERE accountid = ?";
				}
				
				statement = this.link.prepareStatement(query);
				statement.setInt(1, accountID);
				
				resultSet = statement.executeQuery();
				resultSet.next();
				
				int roleid = resultSet.getInt("roleid");
				String name = resultSet.getString("name");
				
				account = new Account(id, email, password, role, roleid, name);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public void delete(int accountID, int roleID, String role) {
		
		try {
			
			String query;
			
			if (role.equals("student")) {
				query = "UPDATE student SET accountid = NULL WHERE studentid = ?";
			} else {
				query = "UPDATE teacher SET accountid = NULL WHERE teacherid = ?";	
			}
			
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setInt(1, roleID);
			statement.executeUpdate();
			
			query = "DELETE FROM account WHERE accountid = ?";
			statement = this.link.prepareStatement(query);
			
			statement.setInt(1, accountID);
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean changePassword(String oldPassword, String newPassword) {
		
		boolean state = true;
		
		try {
			
			String query = "SELECT * FROM account WHERE email = ? AND password = ?";
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setString(1, "admin@sms.com");
			statement.setString(2, oldPassword);
			
			ResultSet resultSet = statement.executeQuery();
			state = resultSet.next();
			
			if (state) {
				
				int id = resultSet.getInt("accountid");
				query = "UPDATE account SET password = ? WHERE accountid = ?";
				
				statement = this.link.prepareStatement(query);
				statement.setString(1, newPassword);
				statement.setInt(2, id);
				
				statement.executeUpdate();
			} 
 			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return state;
	}
}
