package com.group2.data.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.group2.bean.Product;

public class ProductsDAO {

	public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
		PreparedStatement st;
		ResultSet rs;
		Connection connection = DataBaseConnection.getDBConnection();
		st = connection.prepareStatement("SELECT * FROM PRODUCTS ORDER BY pro_id;");
		rs = st.executeQuery();
		List<Product> productList = new ArrayList<Product>();
		int i = 0;
		while(rs.next()) {
			System.out.println("Adding Product to List");
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
		connection.close();
		return productList;
	}
	
	public Product getProductItem(int productId) throws ClassNotFoundException, SQLException {
		PreparedStatement st;
		ResultSet rs;
		Connection connection = DataBaseConnection.getDBConnection();
		st = connection.prepareStatement("SELECT * FROM PRODUCTS AS p "
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
		connection.close();
		return product;
	}

}
