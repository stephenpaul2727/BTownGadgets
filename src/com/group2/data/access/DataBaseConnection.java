package com.group2.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		Connection c = null;
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "123");
		return c;
	}
	
}
