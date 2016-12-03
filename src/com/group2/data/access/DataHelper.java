package com.group2.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataHelper {

	public static void main(String args[]) {
		String INSERT_ORDER_DETAILS_SQL = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, status) VALUES (%d,%d,%d,%f,\'%s\');";
		String s = String.format(INSERT_ORDER_DETAILS_SQL, 15,2,4,40.0,"Pending");
		System.out.println("==>:"+s);
	      /*Connection c = null;
	      Statement stmt = null;
	     
	      	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "123");
	         stmt = c.createStatement();
//	         String sql = "SELECT start_date FROM ORDERS;";
//	         PreparedStatement st = c.prepareStatement(sql);
//	         ResultSet rs1 = st.executeQuery();
//	         
////	         ResultSet rs2 = stmt.executeQuery(sql);
//	         while(rs1.next()) {
//	        	 System.out.println("====>"+rs1.getDate("start_date"));
//	        	 
//	        	 
//	         }
	         stmt.executeUpdate("INSERT INTO ORDERS (cus_id,start_date) VALUES (2,now());", Statement.RETURN_GENERATED_KEYS);
	         ResultSet rs = stmt.getGeneratedKeys();
	         rs.next();
	         int id = rs.getInt(1);
	         System.out.println("Order ID::"+id);
	         c.commit();
	         stmt.close();
//	         st.close();
	         rs.close();
	         c.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Operations successful");*/
	   }
}
