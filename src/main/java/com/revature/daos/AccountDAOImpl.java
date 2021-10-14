package com.revature.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO{

	@Override
	public List<Account> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Accounts;";
			
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			List<Account> list = new ArrayList<>();
			
			while(result.next()) {
				Account account = new Account();
				account.setId(result.getInt("AccountID"));
				account.setName(result.getString("AccountName"));
				account.setBalance(result.getDouble("Balance"));
				account.setDate(result.getString("DateOpened"));
				account.setCustomerID(result.getInt("CustomerID"));
				list.add(account);
			}
			
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> findAccounts(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Accounts WHERE CustomerID = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			List<Account> list = new ArrayList<>();
			
			while(result.next()) {
				Account account = new Account();
				account.setId(result.getInt("AccountID"));
				account.setName(result.getString("AccountName"));
				account.setBalance(result.getDouble("Balance"));
				account.setDate(result.getString("DateOpened"));
				account.setCustomerID(result.getInt("CustomerID"));
				list.add(account);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean addAccount(Account account) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO Accounts (AccountName, Balance, DateOpened, CustomerID) VALUES (?,?,?,?);";
			
			int count = 0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++count, account.getName());
			statement.setDouble(++count, account.getBalance());
			statement.setDate(++count, java.sql.Date.valueOf(account.getDate()));
			statement.setInt(++count, account.getCustomerID());
			
			statement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Account findAccount(int id, String name) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Accounts WHERE CustomerID = ? AND AccountName = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setString(2, name);
			ResultSet result = statement.executeQuery();
			Account account = new Account();
			
			if (result.next()) {
				account.setId(result.getInt("AccountID"));
				account.setName(result.getString("AccountName"));
				account.setBalance(result.getDouble("Balance"));
				account.setDate(result.getString("DateOpened"));
				account.setCustomerID(result.getInt("CustomerID"));
			}
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public Account findAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT * FROM Accounts WHERE AccountID = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Account account = new Account();
			
			if (result.next()) {
				account.setId(result.getInt("AccountID"));
				account.setName(result.getString("AccountName"));
				account.setBalance(result.getDouble("Balance"));
				account.setDate(result.getString("DateOpened"));
				account.setCustomerID(result.getInt("CustomerID"));
			}
			return account;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateAccount(Account account) {
		CallableStatement statement = null;
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "CALL changeBalance(?,?)";
			statement = conn.prepareCall(sql);
			
			statement.setDouble(1, account.getBalance());
			statement.setInt(2, account.getId());
			
			statement.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteAccount(int id) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "DELETE FROM Accounts WHERE AccountID = ?;";
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
