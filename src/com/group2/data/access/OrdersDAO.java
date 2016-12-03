package com.group2.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.group2.bean.CartItem;

public class OrdersDAO {
	
	String INSERT_ORDER_DETAILS_SQL = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, status, emp_id) VALUES (%d,%d,%d,%f,\'%s\',%d);";

	public void insertOrderItems(List<CartItem> cartItems, int cus_id) throws ClassNotFoundException, SQLException {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs;
		int emp_id = 0;
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "123");
		Statement stmt_order = c.createStatement();
		stmt_order.executeUpdate("INSERT INTO ORDERS (cus_id,order_date) VALUES ("+cus_id+",now());", Statement.RETURN_GENERATED_KEYS);
        rs = stmt_order.getGeneratedKeys();
        rs.next();
        int order_id = rs.getInt(1);
        rs = stmt_order.executeQuery("SELECT emp_id FROM EMPLOYEES WHERE designation='Staff' AND emp_id NOT IN (SELECT emp_id FROM ORDER_DETAILS);");
        if(rs.next()) {
        	emp_id = rs.getInt("emp_id");
        	System.out.println("Assigned Employee1==>"+emp_id);
        } else {
        	System.out.println("=======All employees are assigned atleast one order item========");
        	rs = stmt_order.executeQuery("SELECT emp_id FROM ORDER_DETAILS WHERE status != 'Pending' LIMIT 1;");
            if(rs.next()) {
            	emp_id = rs.getInt("emp_id");
            	System.out.println("Assigned Employee2==>"+emp_id);
            } else {
            	System.out.println("=========All employees have Pending items to deliver=========");
            	rs = stmt_order.executeQuery("SELECT count(emp_id),emp_id FROM ORDER_DETAILS GROUP BY emp_id ORDER BY count ASC LIMIT 1;");
            	if(rs.next()) {
            		emp_id = rs.getInt("emp_id");
            		System.out.println("Assigned Employee3==>"+emp_id);
            	}
            }
        }
        
		stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        c.setAutoCommit(false);
        for(CartItem cartItem : cartItems) {
        	int selectedQuantity = cartItem.getSelectedQuantity();
        	float totalUnitPrice = cartItem.getTotalUnitPrice(); 
        	int pro_id = cartItem.getPro_id();
        	String sql = String.format(INSERT_ORDER_DETAILS_SQL, order_id,pro_id,selectedQuantity,totalUnitPrice,"Pending",emp_id);
        	System.out.println("====Adding command to batch====");
        	System.out.println(sql);
        	stmt.addBatch(sql);
        }
        stmt.executeBatch();
        c.commit();
		rs.close();
		stmt_order.close();
		stmt.close();
		c.close();
	}
}
