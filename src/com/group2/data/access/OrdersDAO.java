package com.group2.data.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group2.bean.CartItem;
import com.group2.bean.OrderDetails;

public class OrdersDAO {
	
	String INSERT_ORDER_DETAILS_SQL = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, status, emp_id) VALUES (%d,%d,%d,%.2f,\'%s\',%d);";
	String UPDATE_PRODUCT_QUANTITY = "UPDATE PRODUCTS SET quantity=%d WHERE pro_id=%d;";
	String GET_ORDER_ITEMS_BY_STATUS = "SELECT c.cus_id,c.fname,o.order_id,o.order_date,p.pro_id,p.model_name,"
			+ "p.brand,od.quantity,od.price,od.status,od.start_date,od.end_date FROM ORDERS AS o "
			+ "INNER JOIN ORDER_DETAILS AS od ON o.order_id=od.order_id INNER JOIN PRODUCTS AS p ON p.pro_id=od.pro_id "
			+ "INNER JOIN CUSTOMERS AS c ON o.cus_id=c.cus_id WHERE od.status='%s' AND od.emp_id=%d ORDER BY o.order_date;";
	String UPDATE_DELIVERY_STATUS = "UPDATE ORDER_DETAILS SET status='Delivered',start_date=CURRENT_DATE,end_date=CURRENT_DATE+30 WHERE order_id=%d AND pro_id=%d;";
	String UPDATE_RETURN_STATUS = "UPDATE ORDER_DETAILS SET status='Returned' WHERE order_id=%d AND pro_id=%d;";
	
	public void insertOrderItems(List<CartItem> cartItems, int cus_id) throws ClassNotFoundException, SQLException {
		Statement stmt = null;
		ResultSet rs;
		int emp_id = 0;
		Connection connection = DataBaseConnection.getDBConnection();
		Statement stmt_order = connection.createStatement();
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
        
		stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        connection.setAutoCommit(false);
        for(CartItem cartItem : cartItems) {
        	int selectedQuantity = cartItem.getSelectedQuantity();
        	float totalUnitPrice = cartItem.getTotalUnitPrice(); 
        	int pro_id = cartItem.getPro_id();
        	String insert_sql = String.format(INSERT_ORDER_DETAILS_SQL, order_id,pro_id,selectedQuantity,totalUnitPrice,"Pending",emp_id);
        	int availableQuantity = cartItem.getQuantity();
        	int remianingQuantity = availableQuantity - selectedQuantity;
        	String update_sql = String.format(UPDATE_PRODUCT_QUANTITY, remianingQuantity,pro_id);
        	System.out.println("====Adding command to batch====");
        	System.out.println(insert_sql);
        	System.out.println(update_sql);
        	stmt.addBatch(insert_sql);
        	stmt.addBatch(update_sql);
        }
        stmt.executeBatch();
        
        connection.commit();
		rs.close();
		stmt_order.close();
		stmt.close();
		connection.close();
	}
	
	public List<OrderDetails> getAssignedDeliveriesByStatus(String status, int emp_id) throws ClassNotFoundException, SQLException {
		PreparedStatement st;
		ResultSet rs;
		List<OrderDetails> orderItems = new ArrayList<OrderDetails>();
		Connection connection = DataBaseConnection.getDBConnection();
		String sql = String.format(GET_ORDER_ITEMS_BY_STATUS, status, emp_id);
		System.out.println("====Getting pending deliveries====");
		System.out.println(sql);
		st = connection.prepareStatement(sql);
		rs = st.executeQuery();
		int i = 0;
		while(rs.next()) {
			orderItems.add(new OrderDetails());
			orderItems.get(i).setCus_fname(rs.getString("fname"));
			orderItems.get(i).setOrder_id(rs.getInt("order_id"));
			orderItems.get(i).setPro_id(rs.getInt("pro_id"));
			orderItems.get(i).setModel_name(rs.getString("model_name"));
			orderItems.get(i).setBrand(rs.getString("brand"));
			orderItems.get(i).setSelectedQuantity(rs.getInt("quantity"));
			orderItems.get(i).setTotalUnitPrice(rs.getFloat("price"));
			orderItems.get(i).setStatus(rs.getString("status"));
			orderItems.get(i).setOrder_date(rs.getString("order_date"));
			orderItems.get(i).setStart_date(rs.getString("start_date"));
			orderItems.get(i).setEnd_date(rs.getString("end_date"));
			i++;
		}
		rs.close();
		st.close();
		connection.close();
		return orderItems;
		
	}
	
	public List<OrderDetails> getPastOrders(int cus_id) throws ClassNotFoundException, SQLException {
		PreparedStatement st;
		ResultSet rs;
		Connection connection = DataBaseConnection.getDBConnection();
		st = connection.prepareStatement("SELECT c.fname,o.order_id,o.order_date,od.pro_id,p.model_name,p.brand,od.quantity,od.price,od.start_date,od.end_date,od.status "
				+ "FROM ORDERS AS o INNER JOIN ORDER_DETAILS AS od ON o.order_id=od.order_id "
				+ "INNER JOIN CUSTOMERS AS c ON c.cus_id=o.cus_id INNER JOIN PRODUCTS AS p ON od.pro_id=p.pro_id WHERE c.cus_id=? ORDER BY o.order_date;");
		st.setInt(1,cus_id);
		rs = st.executeQuery();
		List<OrderDetails> orderItems = new ArrayList<OrderDetails>();
		int i = 0;
		while(rs.next()) {
			orderItems.add(new OrderDetails());
			orderItems.get(i).setOrder_id(rs.getInt("order_id"));
			orderItems.get(i).setPro_id(rs.getInt("pro_id"));
			orderItems.get(i).setModel_name(rs.getString("model_name"));
			orderItems.get(i).setBrand(rs.getString("brand"));
			orderItems.get(i).setSelectedQuantity(rs.getInt("quantity"));
			orderItems.get(i).setTotalUnitPrice(rs.getFloat("price"));
			orderItems.get(i).setStatus(rs.getString("status"));
			orderItems.get(i).setStart_date(rs.getString("start_date"));
			orderItems.get(i).setEnd_date(rs.getString("end_date"));
			orderItems.get(i).setOrder_date(rs.getString("order_date"));
			i++;
		}
		rs.close();
		st.close();
		connection.close();
		return orderItems;
	}
	
	public void updateDeliveryStatus(List<OrderDetails> selectedRecords) throws ClassNotFoundException, SQLException {
		Statement stmt = null;
		Connection connection = DataBaseConnection.getDBConnection();
		stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        connection.setAutoCommit(false);
		for(OrderDetails orderItem : selectedRecords) {
			String sql = String.format(UPDATE_DELIVERY_STATUS, orderItem.getOrder_id(), orderItem.getPro_id());
			System.out.println("=====Adding UPDATE command to Batch=======");
			System.out.println(sql);
			stmt.addBatch(sql);
		}
		
		stmt.executeBatch();
		connection.commit();
		stmt.close();
		connection.close();
	}
	
	public void updateReturnStatus(List<OrderDetails> selectedRecords) throws ClassNotFoundException, SQLException {
		Statement stmt = null;
		Connection connection = DataBaseConnection.getDBConnection();
		stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        connection.setAutoCommit(false);
		for(OrderDetails orderItem : selectedRecords) {
			String sql = String.format(UPDATE_RETURN_STATUS, orderItem.getOrder_id(), orderItem.getPro_id());
			System.out.println("=====Adding UPDATE command to Batch=======");
			System.out.println(sql);
			stmt.addBatch(sql);
		}
		
		stmt.executeBatch();
		connection.commit();
		stmt.close();
		connection.close();
	}
}
