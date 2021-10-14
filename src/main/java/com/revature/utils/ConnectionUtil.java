package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e ) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://javafs-210927.cpcqa6gw84y9.us-west-2.rds.amazonaws.com:5432/javafs210927";
		String username = "postgres";
		String password = "password";
		
		return DriverManager.getConnection(url, username, password);
	}
}
