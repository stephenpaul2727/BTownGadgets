package com.group2.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group2.bean.OrderDetails;
import com.group2.bean.Product;

public class ProductsDAO {

	public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
		Connection c = null;
		PreparedStatement st;
		ResultSet rs;
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "123");
		st = c.prepareStatement("SELECT * FROM PRODUCTS;");
		rs = st.executeQuery();
		List<Product> productList = new ArrayList<Product>();
		int i = 0;
		while(rs.next()) {
			productList.add(new Product());
			productList.get(i).setPro_id(rs.getInt("pro_id"));
			productList.get(i).setModel_name(rs.getString("model_name"));
			productList.get(i).setBrand(rs.getString("brand"));
			productList.get(i).setQuantity(rs.getInt("quantity"));
			productList.get(i).setFeedback(rs.getString("feedback"));
			productList.get(i).setRating(rs.getFloat("rating"));
			productList.get(i).setPrice(rs.getFloat("price"));
			productList.get(i).setImagePath(rs.getString("image_path"));
			i++;
		}
		rs.close();
		st.close();
		c.close();
		return productList;
	}
	
	public Product getProductItem(int productId) throws ClassNotFoundException, SQLException {
		Connection c = null;
		PreparedStatement st;
		ResultSet rs;
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "123");
		st = c.prepareStatement("SELECT * FROM PRODUCTS AS p "
				+ "INNER JOIN SPECIFICATION_VALUES AS sv ON p.pro_id=sv.pro_id "
				+ "INNER JOIN SPECIFICATION_TYPES AS st ON sv.spec_id=st.spec_id WHERE p.pro_id=?;");
		st.setInt(1,productId);
		rs = st.executeQuery();
		Product product = new Product();
		Map<String, String> specifications = new HashMap<String, String>();
		int i = 0;
		while(rs.next()) {
			if(i == 0) {
				product.setPro_id(productId);
				product.setModel_name(rs.getString("model_name"));
				product.setBrand(rs.getString("brand"));
				product.setQuantity(rs.getInt("quantity"));
				product.setFeedback(rs.getString("feedback"));
				product.setRating(rs.getFloat("rating"));
				product.setPrice(rs.getFloat("price"));
				product.setImagePath(rs.getString("image_path"));
			}
			specifications.put(rs.getString("spec_name"), rs.getString("spec_value"));
			i++;	
		}
		product.setSpecifications(specifications);
		rs.close();
		st.close();
		c.close();
		return product;
	}
	
	public List<OrderDetails> getPastOrders(int cus_id) throws ClassNotFoundException, SQLException {
		Connection c = null;
		PreparedStatement st;
		ResultSet rs;
		Class.forName("org.postgresql.Driver");
		c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "123");
		st = c.prepareStatement("SELECT c.fname,o.order_id,o.order_date,od.pro_id,p.model_name,p.brand,od.quantity,od.price,od.start_date,od.end_date,od.status "
				+ "FROM ORDERS AS o INNER JOIN ORDER_DETAILS AS od ON o.order_id=od.order_id "
				+ "INNER JOIN CUSTOMERS AS c ON c.cus_id=o.cus_id INNER JOIN PRODUCTS AS p ON od.pro_id=p.pro_id WHERE c.cus_id=?;");
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
			orderItems.get(i).setQuantity(rs.getInt("quantity"));
			orderItems.get(i).setPrice(rs.getFloat("price"));
			orderItems.get(i).setStatus(rs.getString("status"));
			orderItems.get(i).setStart_date(rs.getString("start_date"));
			orderItems.get(i).setEnd_date(rs.getString("end_date"));
			orderItems.get(i).setOrder_date(rs.getString("order_date"));
			i++;
		}
		rs.close();
		st.close();
		c.close();
		return orderItems;
	}
}
