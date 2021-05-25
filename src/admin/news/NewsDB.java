package admin.news;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class NewsDB 
{
  private Connection link;
	
	public NewsDB(DataSource dataSource) throws SQLException {
		
		this.link = dataSource.getConnection();
	}
	
	
	public List<News> listNews()
	{
		List<News> news = new ArrayList<News>();
         try {
			
			String query = "SELECT * FROM news";
			PreparedStatement statement = this.link.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				int newsID = resultSet.getInt("newsid");
				String title = resultSet.getString("newstitle");
				String content = resultSet.getString("newscontent");
				
				news.add(new News(newsID, title, content));
			}
			
			resultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return news;
    }
	
	
	
	public void addNews(News theNews)
	{

          try {
			
			String query = "INSERT INTO news(newsid,newstitle,newscontent) VALUES(DEFAULT,?,?)";
			
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setString(1, theNews.getNewsTitle());
			statement.setString(2, theNews.getNewsContent());
			statement.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
     public News loadNews(int newsID) {
		
		News news=null;
		
		try {
			
			String query = "SELECT * FROM news WHERE newsid=?";
			PreparedStatement statement = this.link.prepareStatement(query);
			statement.setInt(1, newsID);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			
			String title = resultSet.getString("newstitle");
			String content = resultSet.getString("newscontent");
			news=new News(newsID, title, content);
			resultSet.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return news;
	}

	public void updateNews(News theNews)  
	{
        try {
			
			String query = "UPDATE news SET newstitle=?,newscontent=? WHERE newsid=? LIMIT 1";
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setString(1, theNews.getNewsTitle());
			statement.setString(2, theNews.getNewsContent());
			statement.setInt(3, theNews.getNewsId());
			
			statement.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public void deleteNews(int newsID) {
		
		try {
			
			String query = "DELETE FROM news WHERE newsid=? LIMIT 1";
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setInt(1, newsID);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

