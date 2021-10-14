package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Customer;
import com.revature.utils.AES256;
import com.revature.utils.ConnectionUtil;

public class CustomerDAOImpl implements CustomerDAO{

	@Override
	public List<Customer> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Customers;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Customer> list = new ArrayList<>();
			
			while(result.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(result.getInt("CustomerID"));
				customer.setFirstName(result.getString("FirstName"));
				customer.setLastName(result.getString("LastName"));
				customer.setPhone(result.getString("CustomerPhone"));
				customer.setEmail(result.getString("CustomerEmail"));
				customer.setLogin(result.getString("Login"));
				customer.setPassword(AES256.decrypt(result.getString("Password")));
				customer.setPower(result.getInt("PowerLevel"));
				list.add(customer);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Customer findByCredentials(String login, String password) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Customers WHERE Login = ? AND \"password\" = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, AES256.encrypt(password));
			ResultSet result = statement.executeQuery();
			
			Customer customer = new Customer();
			
			if (result.next()) {
				customer.setCustomerID(result.getInt("CustomerID"));
				customer.setFirstName(result.getString("FirstName"));
				customer.setLastName(result.getString("LastName"));
				customer.setPhone(result.getString("CustomerPhone"));
				customer.setEmail(result.getString("CustomerEmail"));
				customer.setLogin(result.getString("Login"));
				customer.setPassword(AES256.decrypt(result.getString("Password")));
				customer.setPower(result.getInt("PowerLevel"));
			}
			return customer;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	

	@Override
	public boolean updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Customers (firstname, lastname, customerphone, customeremail, login, \"password\", PowerLevel) VALUES (?,?,?,?,?,?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++count, customer.getFirstName());
			statement.setString(++count, customer.getLastName());
			statement.setString(++count, customer.getPhone());
			statement.setString(++count, customer.getEmail());
			statement.setString(++count, customer.getLogin());
			statement.setString(++count, AES256.encrypt(customer.getPassword()));
			statement.setInt(++count, customer.getPower());
			
			statement.execute();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
