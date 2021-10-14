package com.revature.daos;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Application;
import com.revature.utils.ConnectionUtil;

public class ApplicationDAOImpl implements ApplicationDAO {

	@Override
	public List<Application> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Applications;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Application> list = new ArrayList<>();
			
			while(result.next()) {
				Application application = new Application();
				application.setId(result.getInt("ApplicationID"));
				application.setAccountName(result.getString("AccountName"));
				application.setStartingBalance(result.getDouble("AccountStartingBalance"));
				application.setCustomerID(result.getInt("CustomerID"));
				list.add(application);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Application findApplication(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Applications WHERE ApplicationID = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			
			Application application = new Application();
			
			if (result.next()) {
				application.setId(result.getInt("ApplicationID"));
				application.setAccountName(result.getString("AccountName"));
				application.setStartingBalance(result.getDouble("AccountStartingBalance"));
				application.setCustomerID(result.getInt("CustomerID"));
			}
			return application;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addApplication(Application application) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Applications (AccountName, AccountStartingBalance, CustomerID) VALUES (?,?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++count, application.getAccountName());
			statement.setDouble(++count, application.getStartingBalance());
			statement.setInt(++count, application.getCustomerID());
			
			statement.execute();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteApplication(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM Applications WHERE ApplicationID = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


}
