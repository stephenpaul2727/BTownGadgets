package com.group2.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginCheckDAO {
	
	public static final String CUSTOMER = "Customer";
	public static final String DESIGNATION = "designation";

	public String verifyLoginDetails(String username, String password) {
		Connection c = null;
		String designation = null;
		PreparedStatement st;
		ResultSet rs;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "123");
			st=c.prepareStatement("SELECT * FROM CUSTOMERS WHERE uname=? AND pwd=?");
			st.setString(1,username);
			st.setString(2,password);
			rs = st.executeQuery();
			if(rs.next()) {
				return CUSTOMER;
			} else {
				st = c.prepareStatement("SELECT designation FROM EMPLOYEES WHERE uname=? AND pwd=?");
				st.setString(1,username);
				st.setString(2,password);
				rs = st.executeQuery();
				if(rs.next()) {
					designation = rs.getString(DESIGNATION);
					System.out.println("Designation::"+designation);
				}
			}
			rs.close();
			st.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		return designation;
	}
}
