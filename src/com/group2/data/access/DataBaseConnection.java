package com.group2.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

	public static Connection getDBConnection() throws ClassNotFoundException, SQLException {
		Connection c = null;
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection("jdbc:postgresql://ec2-54-221-244-196.compute-1.amazonaws.com:5432/dborp54gjfaa05?user=natamoqhwnfvsu&password=b9e0bb9f16357904cf05e3832d6444225e91f6d17c477d238a73e789f2b208b4&ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
		return c;
	}
	
}
