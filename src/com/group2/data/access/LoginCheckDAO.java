package com.group2.data.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.group2.bean.Customer;
import com.group2.bean.Employee;

public class LoginCheckDAO {

	public static final String CUSTOMER = "Customer";
	public static final String DESIGNATION = "designation";

	public String verifyLoginDetails(String username, String password) throws ClassNotFoundException, SQLException {
		String designation = null;
		PreparedStatement st;
		ResultSet rs;
		Connection connection = DataBaseConnection.getDBConnection();
		st = connection.prepareStatement("SELECT * FROM CUSTOMERS WHERE uname=? AND pwd=?");
		st.setString(1, username);
		st.setString(2, password);
		rs = st.executeQuery();
		if (rs.next()) {
			return CUSTOMER;
		} else {
			st = connection.prepareStatement("SELECT designation FROM EMPLOYEES WHERE uname=? AND pwd=? and emp_id NOT IN (SELECT emp_id FROM DELETED_EMPLOYEES);");
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
		return designation;
	}

	public Customer getCustomerDetails(String uname) throws ClassNotFoundException, SQLException {
		Customer customer = new Customer();
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
		return customer;
	}

	public List<Customer> getAllCustomersNames() throws ClassNotFoundException, SQLException {
		List<Customer> customerList = new ArrayList<Customer>();
		Connection connection = DataBaseConnection.getDBConnection();
		PreparedStatement st = connection.prepareStatement("SELECT fname,lname FROM CUSTOMERS;");
		ResultSet rs = st.executeQuery();
		int i = 0;
		while (rs.next()) {
			customerList.add(new Customer());
			customerList.get(i).setFname(rs.getString("fname"));
			customerList.get(i).setLname(rs.getString("lname"));
			System.out.println(i+"--Customer Added::"+customerList.get(i).toString());
			i++;
		}
		System.out.println("Customer List SIZE::"+customerList.size());
		rs.close();
		st.close();
		connection.close();
		return customerList;
	}

	public Employee getEmployeeDetails(String uname) throws ClassNotFoundException, SQLException {
		Employee employee = new Employee();
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
		return employee;
	}

	public List<Employee> getAllEmployees() throws ClassNotFoundException, SQLException {
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection connection = DataBaseConnection.getDBConnection();
		String sql = "SELECT * FROM EMPLOYEES WHERE designation='Staff' AND emp_id NOT IN (SELECT emp_id FROM DELETED_EMPLOYEES);";
		PreparedStatement st = connection.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		int i = 0;
		while (rs.next()) {
			employeeList.add(new Employee());
			employeeList.get(i).setEmp_id(rs.getInt("emp_id"));
			employeeList.get(i).setFname(rs.getString("fname"));
			employeeList.get(i).setLname(rs.getString("lname"));
			employeeList.get(i).setEmail(rs.getString("email"));
			i++;
		}
		System.out.println("Employee List SIZE::"+employeeList.size());
		rs.close();
		st.close();
		connection.close();
		return employeeList;
	}

	public void addCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES ('%s','%s','%s','%s','%s','%s');";
		Connection connection = DataBaseConnection.getDBConnection();
		String insertCustomer = String.format(sql, customer.getFname(), customer.getLname(), customer.getAddress(),
				customer.getEmail(), customer.getUname(), customer.getPassword());
		PreparedStatement st = connection.prepareStatement(insertCustomer);
		st.executeUpdate();
		st.close();
		connection.close();

	}

	public void deleteEmployee(int empID) throws ClassNotFoundException, SQLException {
		Statement stmt = null;
		Connection connection = DataBaseConnection.getDBConnection();
		stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        connection.setAutoCommit(false);
//		String deleteEmp = "DELETE FROM EMPLOYEES WHERE emp_id=%d;";
		String insertDeletedEmp = "INSERT INTO DELETED_EMPLOYEES (emp_id) VALUES (%d);";
//		stmt.addBatch(String.format(deleteEmp, empID));
		stmt.addBatch(String.format(insertDeletedEmp, empID));
		stmt.executeBatch();
		connection.commit();
		stmt.close();
		connection.close();
	}
}
