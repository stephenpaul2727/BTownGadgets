package com.group2.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			i++;
		}
		rs.close();
		st.close();
		c.close();
		return productList;
	}
}
