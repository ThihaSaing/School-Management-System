package guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;



public class GuestNewsDB {
	
private Connection link;
	
	public GuestNewsDB(DataSource dataSource) throws SQLException {
		
		this.link = dataSource.getConnection();
	}
	
	
	public List<GuestNews> listNews()
	{
		List<GuestNews> news = new ArrayList<GuestNews>();
         try {
			
			String query = "SELECT * FROM news";
			PreparedStatement statement = this.link.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				int newsID = resultSet.getInt("newsid");
				String title = resultSet.getString("newstitle");
				String content = resultSet.getString("newscontent");
				
				news.add(new GuestNews(newsID, title, content));
			}
			
			resultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return news;
    }
	
	 public GuestNews loadGuestNews(int newsID) {
			
			GuestNews gnews=null;
			
			try {
				
				String query = "SELECT * FROM news WHERE newsid=?";
				PreparedStatement statement = this.link.prepareStatement(query);
				statement.setInt(1, newsID);
				
				ResultSet resultSet = statement.executeQuery();
				resultSet.next();
				
				String title = resultSet.getString("newstitle");
				String content = resultSet.getString("newscontent");
				gnews=new GuestNews(newsID, title, content);
				resultSet.close();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return gnews;
		}
	 
	 public void detailNews(GuestNews gNews)  
		{
	        try {
				
				String query = "SELECT * FROM news WHERE newsid=?";
				PreparedStatement statement = this.link.prepareStatement(query);
				
				statement.setString(1, gNews.getNewsTitle());
				statement.setString(2, gNews.getNewsContent());
				statement.setInt(3, gNews.getNewsId());
				
				statement.executeUpdate();
							
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	 
	 public List<GuestNews> loadForIndex() {
		 
		 List<GuestNews> guestNews = new ArrayList<GuestNews>();
		 
		 try {
			 
			 String query = "SELECT * FROM news LIMIT 4";
			 PreparedStatement statement = this.link.prepareStatement(query);
			 
			 ResultSet resultSet = statement.executeQuery();
			 
			 while(resultSet.next()) {
				 
				 int newsid = resultSet.getInt("newsid");
				 String newstitle = resultSet.getString("newstitle");
				 String newscontent = resultSet.getString("newscontent");
				 
				 guestNews.add(new GuestNews(newsid, newstitle, newscontent));
			 }
			 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
		 return guestNews;
	 }
	
  /*   public GuestNews detailNews(int newsID) {
		
		GuestNews news=null;
		
		try {
			
			String query = "SELECT * FROM news WHERE newsid=?";
			PreparedStatement statement = this.link.prepareStatement(query);
			statement.setInt(1, newsID);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			
			String title = resultSet.getString("newstitle");
			String content = resultSet.getString("newscontent");
			news=new GuestNews(newsID, title, content);
			resultSet.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return news;
	}
*/
}
