package admin.schedule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ScheduleDB {
	
private Connection link;
	
	public ScheduleDB(DataSource dataSource) throws SQLException {
		
		this.link = dataSource.getConnection();
	}
	
	
	public List<Schedule> listSchedule()
	{
		List<Schedule> scheduleList = new ArrayList<Schedule>();
         try {
			
			String query = "SELECT * FROM schedule";
			PreparedStatement statement = this.link.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				int scheduleId = resultSet.getInt("scheduleid");
				String subjectName = resultSet.getString("subjectname");
				String timeFrom = resultSet.getString("timefrom");
				String timeTo = resultSet.getString("timeto");
				String day = resultSet.getString("day");
				String className = resultSet.getString("classname");
				
				scheduleList.add(new Schedule(scheduleId, subjectName, timeFrom, timeTo, day, className));
			}
			
			resultSet.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return scheduleList;
    }
	
	
	
	public void addSchedule(Schedule theSchedule)
	{

          try {
			
			String query = "INSERT INTO schedule(scheduleid,subjectname,timefrom,timeto,day,classname) VALUES(DEFAULT,?,?,?,?,?)";
			
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setString(1, theSchedule.getSubjectName());
			statement.setString(2, theSchedule.getTimeFrom());
			statement.setString(3, theSchedule.getTimeTo());
			statement.setString(4, theSchedule.getDay());
			statement.setString(5, theSchedule.getClassName());
			statement.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
     public Schedule loadSchedule(int theScheduleId) {
		
		Schedule schedule=null;
		
		try {
			
			String query = "SELECT * FROM schedule WHERE scheduleid=?";
			PreparedStatement statement = this.link.prepareStatement(query);
			statement.setInt(1, theScheduleId);
			
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			
			
			String subjectName = resultSet.getString("subjectname");
			String timeFrom = resultSet.getString("timefrom");
			String timeTo = resultSet.getString("timeto");
			String day = resultSet.getString("day");
			String className = resultSet.getString("classname");
			schedule=new Schedule(theScheduleId, subjectName, timeFrom, timeTo, day, className);
			resultSet.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return schedule;
	}

	public void updateSchedule(Schedule theSchedule)  
	{
        try {
			
			String query = "UPDATE schedule SET subjectname=?,timefrom=?,timeto=?,day=?,classname=? WHERE scheduleid=? LIMIT 1";
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setString(1, theSchedule.getSubjectName());
			statement.setString(2, theSchedule.getTimeFrom());
			statement.setString(3, theSchedule.getTimeTo());
			statement.setString(4, theSchedule.getDay());
			statement.setString(5, theSchedule.getClassName());
			statement.setInt(6, theSchedule.getScheduleId());
			statement.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    public void deleteSchedule(int scheduleId) {
		
		try {
			
			String query = "DELETE FROM schedule WHERE scheduleid=? LIMIT 1";
			PreparedStatement statement = this.link.prepareStatement(query);
			
			statement.setInt(1, scheduleId);
			
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
