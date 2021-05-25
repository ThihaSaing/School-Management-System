package admin.staff;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

public class StaffDB {
	
	private Connection connection;

	public StaffDB(DataSource dataSource) throws SQLException {

		this.connection = dataSource.getConnection();
	}

	public List<Staff> getStaffs() {

		List<Staff> stafflist = new ArrayList<Staff>();
		try {

			String query = "SELECT * FROM Staff ORDER BY staffid";
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("staffid");
				String staffname = resultSet.getString("staffname");
				String position = resultSet.getString("position");
				int salary = resultSet.getInt("salary");
				String phoneno = resultSet.getString("phoneno");
				String address = resultSet.getString("address");
				Date dob = resultSet.getDate("dob");
				String nric = resultSet.getString("nric");

				stafflist.add(new Staff(id, staffname, position, salary, phoneno, address, dob, nric));
			}
			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stafflist;
	}

	public Staff loadStaff(int staffID) throws ParseException {

		Staff staff = null;

		try {

			String query = "SELECT * FROM Staff WHERE staffid = ?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setInt(1, staffID);

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();

			String staffName = resultSet.getString("staffname");
			String position = resultSet.getString("position");
			int salary = Integer.parseInt(resultSet.getString("salary"));
			String phNumber = resultSet.getString("phoneno");
			String address = resultSet.getString("address");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date dateofBirth = format.parse(resultSet.getString("dob"));
			java.sql.Date dob = new java.sql.Date(dateofBirth.getTime());
			String nrc = resultSet.getString("nric");

			staff = new Staff(staffID, staffName, position, salary, phNumber, address, dob, nrc);

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return staff;
	}

	public void addStaff(Staff staff) {

		try {

			String query = "INSERT INTO Staff VALUES(DEFAULT, ? , ? , ? , ? , ? , ? , ?)";

			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setString(1, staff.getStaffname());
			statement.setString(2, staff.getPosition());
			statement.setInt(3, staff.getSalary());
			statement.setString(4, staff.getPhoneno());
			statement.setString(5, staff.getAddress());
			statement.setDate(6, (java.sql.Date) staff.getDob());
			statement.setString(7, staff.getNric());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void updateBook(Staff staff) {

		try {

			String query = "UPDATE Staff SET staffname = ?, position = ?, salary = ?, phoneno = ?, address = ?, dob = ?, nric = ? WHERE staffid = ? LIMIT 1";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setString(1, staff.getStaffname());
			statement.setString(2, staff.getPosition());
			statement.setInt(3, staff.getSalary());
			statement.setString(4, staff.getPhoneno());
			statement.setString(5, staff.getAddress());
			statement.setDate(6, (java.sql.Date) staff.getDob());
			statement.setString(7, staff.getNric());
			
			statement.setInt(8, staff.getStaffid());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStaff(int staffID) {

		try {

			String query = "DELETE FROM Staff WHERE staffid = ? LIMIT 1";
			PreparedStatement statement = this.connection.prepareStatement(query);

			statement.setInt(1, staffID);

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
