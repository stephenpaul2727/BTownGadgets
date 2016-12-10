package com.group2.data.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.group2.bean.Customer;
import com.group2.bean.Employee;

public class LoginCheckDAO {

	public static final String CUSTOMER = "Customer";
	public static final String DESIGNATION = "designation";

	public String verifyLoginDetails(String username, String password) {
		String designation = null;
		PreparedStatement st;
		ResultSet rs;
		try {
			Connection connection = DataBaseConnection.getDBConnection();
			st = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE uname=? AND pwd=?");
			st.setString(1, username);
			st.setString(2, password);
			rs = st.executeQuery();
			if (rs.next()) {
				return CUSTOMER;
			} else {
				st = connection.prepareStatement("SELECT designation FROM EMPLOYEES WHERE uname=? AND pwd=?");
				st.setString(1, username);
				st.setString(2, password);
				rs = st.executeQuery();
				if (rs.next()) {
					designation = rs.getString(DESIGNATION);
					System.out.println("Designation::" + designation);
				}
			}
			rs.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return designation;
	}

	public Customer getCustomerDetails(String uname) {
		Customer customer = new Customer();
		try {
			Connection connection = DataBaseConnection.getDBConnection();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE uname=?");
			st.setString(1, uname);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				customer.setCus_id(rs.getInt("cus_id"));
				customer.setFname(rs.getString("fname"));
				customer.setLname(rs.getString("lname"));
				customer.setEmail(rs.getString("email"));
				customer.setAddress(rs.getString("address"));
				customer.setUname(uname);
			}
			rs.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return customer;
	}

	public Employee getEmployeeDetails(String uname) {
		Employee employee = new Employee();
		try {
			Connection connection = DataBaseConnection.getDBConnection();
			PreparedStatement st = connection.prepareStatement("SELECT * FROM EMPLOYEES WHERE uname=?");
			st.setString(1, uname);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				employee.setEmp_id(rs.getInt("emp_id"));
				employee.setFname(rs.getString("fname"));
				employee.setLname(rs.getString("lname"));
				employee.setEmail(rs.getString("email"));
				employee.setAddress(rs.getString("address"));
				employee.setUname(uname);
				employee.setDesignation(rs.getString("designation"));
			}
			rs.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		return employee;
	}

	public void addCustomer(Customer customer) {
		String sql = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
				+ "('%s','%s','%s','%s','%s','%s');";
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = DataBaseConnection.getDBConnection();
			String insertCustomer = String.format(sql, customer.getFname(), customer.getLname(), customer.getAddress(),
					customer.getEmail(), customer.getUname(), customer.getPassword());
			PreparedStatement st = connection.prepareStatement(insertCustomer);
			st.executeUpdate();
			st.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}

	}
}
