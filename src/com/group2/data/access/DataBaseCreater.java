package com.group2.data.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseCreater {

	public static void main(String args[]) {
	      Connection c = null;
	      Statement stmt = null;
	     
	      String drop_cus = "DROP TABLE IF EXISTS CUSTOMERS;";
	      String drop_emp = "DROP TABLE IF EXISTS EMPLOYEES;";
	      String drop_cat = "DROP TABLE IF EXISTS CATEGORY;";
	      String drop_pro = "DROP TABLE IF EXISTS PRODUCTS;";
	      String drop_ord = "DROP TABLE IF EXISTS ORDERS;";
	      String drop_ord_details = "DROP TABLE IF EXISTS ORDER_DETAILS;";
	      String drop_spec_type = "DROP TABLE IF EXISTS SPECIFICATION_TYPES;";
	      String drop_spec_value = "DROP TABLE IF EXISTS SPECIFICATION_VALUES;";
	      String drop_type_desig = "DROP TYPE desig_type_enum;";
	      String drop_type_cat = "DROP TYPE cat_type_enum;";
	      String drop_type_status = "DROP TYPE status_type_enum;";
	      
	      String create_cus = "CREATE TABLE CUSTOMERS("
	      		+ "cus_id SERIAL PRIMARY KEY, fname VARCHAR(20), lname VARCHAR(20), address TEXT,"
	      		+ "email VARCHAR(20), uname VARCHAR(20), pwd VARCHAR(20));";
	      
	      String designation_type_enum = "CREATE TYPE desig_type_enum AS ENUM ('Staff', 'Supervisor', 'SalesManager');";
	      String create_emp = "CREATE TABLE EMPLOYEES("
		      		+ "emp_id SERIAL PRIMARY KEY,fname VARCHAR(20), lname VARCHAR(20), address TEXT,"
		      		+ "email VARCHAR(20), designation desig_type_enum, uname VARCHAR(20), pwd VARCHAR(20));";
	      
	      String category_type_enum = "CREATE TYPE cat_type_enum AS ENUM ('Mobile', 'Camera', 'Watch', 'Laptop');";
	      String create_cat = "CREATE TABLE CATEGORY(cat_id SERIAL PRIMARY KEY, cat_name cat_type_enum);";
	      
	      String create_pro = "CREATE TABLE PRODUCTS("
		      		+ "pro_id SERIAL PRIMARY KEY, cat_id INT NOT NULL, model_name VARCHAR(20) NOT NULL, brand VARCHAR(20),"
		      		+ "quantity INT, feedback VARCHAR(50), rating INT, price DECIMAL NOT NULL, image_path VARCHAR(50),"
		      		+ "FOREIGN KEY (cat_id) REFERENCES CATEGORY(cat_id));";
	      
	      String create_ord = "CREATE TABLE ORDERS(order_id SERIAL PRIMARY KEY, cus_id INT REFERENCES CUSTOMERS, order_date DATE);";
	      
	      String status_type_enum = "CREATE TYPE status_type_enum AS ENUM ('Pending', 'Delivered', 'Returned');";
	      String create_ord_details = "CREATE TABLE ORDER_DETAILS("
		      		+ "order_id INT REFERENCES ORDERS, pro_id INT REFERENCES PRODUCTS, quantity INT, price DECIMAL NOT NULL,"
		      		+ "start_date DATE, end_date DATE, status status_type_enum, emp_id INT REFERENCES EMPLOYEES, PRIMARY KEY(order_id, pro_id));";
	      
	      String create_spec_types = "CREATE TABLE SPECIFICATION_TYPES(spec_id SERIAL PRIMARY KEY, spec_name VARCHAR(20) NOT NULL);";
	      
	      String create_spec_values = "CREATE TABLE SPECIFICATION_VALUES("
	      		+ "pro_id INT REFERENCES PRODUCTS, spec_id INT REFERENCES SPECIFICATION_TYPES,"
	      		+ "spec_value VARCHAR(20) NOT NULL, PRIMARY KEY(pro_id, spec_id));";
	      
	      String insert_cus_1 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
	      		+ "('Karthikeya','Dulla','Address-1','karthik@gmail.com','karthik123','karthik@123');";
	      String insert_cus_2 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
		      		+ "('Prudhvi','Dachapalli','Address-2','prudhvi@gmail.com','prudhvi234','prudhvi@234');";
		  String insert_cus_3 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
	      		+ "('Vinita','Chakilam','512, W Northlane Drive, Bloomington, Indiana, 474404','vinita@gmail.com','vinita123','vinita@123');";
	      String insert_cus_4 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
	      		+ "('Priyanka','Ramamurthy','513, W Northlane Drive, Bloomington, Indiana, 47404','priyanka@gmail.com','priyanka123','priyanka@123');";
	      String insert_cus_5 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
	      		+ "('Nilima','Sahoo','1750 N Range road, Fountain Park apartments, Bloomington, Indiana, 47408','nilima@gmail.com','nilima123','nilima@123');";
	      String insert_cus_6 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
	      		+ "('Stephan','Paul','514, W Northlane Drive, Bloomington, Indiana, 47404','stephan@gmail.com','stephan123','stephan@123');";
	      String insert_cus_7 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
	      		+ "('Divya','Shri','1751 N Range Road, Fountain Park Apartments, Bloomingon, Indiana, 47408','divya@gmail.com','divya123','divya@123');";
	      String insert_cus_8 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
	      		+ "('Janani','Muppala','512, Hoosier Court Apartments, Bloomington, Indiana, 47405','janani@gmail.com','janani123','janani@123');";
	      String insert_cus_9 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
	      		+ "('Jayadev','Kumar','234, Hoosier Court Apartments, Bloomington, Indiana','jayadev@gmail.com','jayadev123','jayadev@123');";
	      String insert_cus_10 = "INSERT INTO CUSTOMERS (fname,lname,address,email,uname,pwd) VALUES "
	      		+ "('Anvesh','Nayan','456, Hoosier Court Apartments, Bloomington, Indiana','anvesh@gmail.com','anvesh123','anvesh@123');";
	      
	      String insert_emp_a = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Rahul','Raghunathan','Address-a','rahul@gmail.com','Staff','rahul123','rahul@123');";
	      String insert_emp_b = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Anil','Shivaraj','Address-b','anil@gmail.com','Supervisor','anil123','anil@123');";
	      String insert_emp_c = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Hari','Chatti','Address-c','hari@gmail.com','SalesManager','hari123','hari@123');";
	      String insert_emp_d = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Shalmali','Pawde','Address-d','shalmali@gmail.com','Staff','shalmali123','shalmali@123');";
	      String insert_emp_e = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Monika','Goyal','Address-e','monika@gmail.com','Supervisor','monika123','monika@123');";
		  String insert_emp_f = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Rama','Kumar','Address-e','rama@gmail.com','Staff','rama123','rama@123');";
		  String insert_emp_g = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Steve','Johnson','Address-e','steve@gmail.com','SalesManager','steve123','steve@123');";
		  String insert_emp_h = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Padmini','Patlolla','Address-e','padmini@gmail.com','Staff','padmini123','padmini@123');";
		  String insert_emp_i = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Bhavana','Yalla','Address-e','bhavana@gmail.com','Supervisor','bhavana123','bhavana@123');";
		  String insert_emp_j = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Sindhuja','Beram','Address-e','sindhuja@gmail.com','SalesManager','sindhuja123','sindhuja@123');";
		  String insert_emp_k = "INSERT INTO EMPLOYEES (fname,lname,address,email,designation,uname,pwd) VALUES "
		      		+ "('Sushmitha','Vuppula','Address-e','sushmitha@gmail.com','Staff','sushmitha123','sushmitha@123');";
	      
	      String insert_cat_1 = "INSERT INTO CATEGORY (cat_name) VALUES ('Mobile');";
	      String insert_cat_2 = "INSERT INTO CATEGORY (cat_name) VALUES ('Camera');";
	      String insert_cat_3 = "INSERT INTO CATEGORY (cat_name) VALUES ('Watch');";
	      String insert_cat_4 = "INSERT INTO CATEGORY (cat_name) VALUES ('Laptop');";
	      
	      String insert_pro_1 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (1,'iPhone7','Apple',70,65,'Images/iphone.jpeg');";
	      String insert_pro_2 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (1,'pixel','Google',50,59,'Images/pixel.jpeg');";
	      String insert_pro_3 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (1,'Note7','Samsung',5,30,'Images/note7.jpg');";
	      String insert_pro_4 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (2,'D5200','Nikon',30,25,'Images/nikon.jpg');";
	      String insert_pro_5 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (2,'eos_7D','Canon',35,28,'Images/canon.jpg');";
	      String insert_pro_6 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (3,'moto_360','Motorola',120,18,'Images/moto360.jpg');";
	      String insert_pro_7 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (3,'q_wander','Fossil',60,14,'Images/fossil.jpeg');";
	      String insert_pro_8 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (4,'studio','Dell',12,45,'Images/dell.jpg');";
	      String insert_pro_9 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (4,'blade_stealth','Razer',4,89,'Images/razer.jpg');";
	      String insert_pro_10 = "INSERT INTO PRODUCTS (cat_id,model_name,brand,quantity,price,image_path) VALUES (4,'vaio','Sony',20,37,'Images/sony.jpg');";
	      
	      String insert_ord_1 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (1, '2016-05-20');";
	      String insert_ord_2 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (1, '2016-11-25');";
	      String insert_ord_3 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (2, '2016-12-02');";
	      String insert_ord_4 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (3, '2016-04-24');";
	      String insert_ord_5 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (4, '2016-05-04');";
	      String insert_ord_6 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (5, '2016-06-12');";
	      String insert_ord_7 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (6, '2016-07-16');";
	      String insert_ord_8 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (7, '2016-08-19');";
	      String insert_ord_9 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (8, '2016-09-03');";
	      String insert_ord_10 = "INSERT INTO ORDERS (cus_id, order_date) VALUES (9, '2016-11-30');";
	      
	      String insert_ord_det_1 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
	      		+ "VALUES (1,2,3,1770,'2016-05-24','2016-06-24','Returned',1);";
	      String insert_ord_det_2 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
	      		+ "VALUES (1,4,2,1400,'2016-05-24','2016-06-24','Returned',1);";
	      String insert_ord_det_3 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
	      		+ "VALUES (2,10,1,420,null,null,'Pending',4);";
	      String insert_ord_det_4 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
	      		+ "VALUES (3,6,2,360,null,null,'Pending',11);";
	      String insert_ord_det_5 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (4,1,2,1300,'2016-04-25','2016-05-23','Returned',8);";
	      String insert_ord_det_6 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (4,2,2,1180,'2016-04-25','2016-05-23','Returned',6);";
	      String insert_ord_det_7 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (5,3,2,1000,'2016-05-09','2016-06-08','Returned',4);";
	      String insert_ord_det_8 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (5,1,1,650,'2016-05-09','2016-06-08','Returned',11);";
	      String insert_ord_det_9 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (6,5,1,600,'2016-06-15','2016-07-14','Returned',8);";
	      String insert_ord_det_10 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (6,7,1,140,'2016-06-15','2016-07-14','Returned',6);";
	      String insert_ord_det_11 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (7,4,3,2100,'2016-07-19','2016-08-19','Returned',4);";
	      String insert_ord_det_12 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (7,8,1,450,'2016-07-19','2016-08-19','Returned',4);";
	      String insert_ord_det_13 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (8,8,4,1800,'2016-08-23','2016-09-22','Returned',6);";
	      String insert_ord_det_14 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (8,1,1,650,'2016-08-23','2016-09-22','Delivered',8);";
	      String insert_ord_det_15 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (9,2,1,590,'2016-09-07','2016-10-06','Returned',11);";
	      String insert_ord_det_16 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (9,10,1,420,'2016-09-07','2016-10-06','Delivered',11);";
	      String insert_ord_det_17 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (9,3,1,500,'2016-09-07','2016-10-06','Delivered',8);";
	      String insert_ord_det_18 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (10,7,3,420,'2016-12-03','2017-1-02','Delivered',6);";
	      String insert_ord_det_19 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (10,5,1,600,null,null,'Pending',6);";
	      String insert_ord_det_20 = "INSERT INTO ORDER_DETAILS (order_id, pro_id, quantity, price, start_date, end_date, status, emp_id) "
		      		+ "VALUES (10,8,1,450,null,null,'Pending',4);";
	      
	      
	      String insert_spec_type_1 = "INSERT INTO SPECIFICATION_TYPES (spec_name) VALUES ('Megapixel_count');";
	      String insert_spec_type_2 = "INSERT INTO SPECIFICATION_TYPES (spec_name) VALUES ('OS');";
	      String insert_spec_type_3 = "INSERT INTO SPECIFICATION_TYPES (spec_name) VALUES ('RAM');";
	      String insert_spec_type_4 = "INSERT INTO SPECIFICATION_TYPES (spec_name) VALUES ('Processor');";
	      String insert_spec_type_5 = "INSERT INTO SPECIFICATION_TYPES (spec_name) VALUES ('Battery');";
	      String insert_spec_type_6 = "INSERT INTO SPECIFICATION_TYPES (spec_name) VALUES ('Storage');";
	      
	      String insert_spec_value = "INSERT INTO SPECIFICATION_VALUES (pro_id, spec_id, spec_value) VALUES (1,1,'12.3MP'),"
	      		+ "(1,2,'IOS 10.0.1'),(1,3,'2GB'),(1,4,'A10 Fusion'),(1,5,'1960mAh'),(1,6,'32GB'),"
	      		+ "(2,1,'12.3MP'),(2,2,'Android OS v7.1'),(2,3,'4GB'),(2,4,'Qualcomm MSM8996'),(2,5,'2770mAh'),(2,6,'32GB'),"
	      		+ "(3,1,'12.3MP'),(3,2,'Android OS v6.0.1'),(3,3,'4GB'),(3,4,'Exynos 8890 Octa'),(3,5,'3500mAh'),(3,6,'64GB'),"
	      		+ "(4,1,'24.1MP'),(4,5,'2500mAh'),(4,6,'8GB'),(5,1,'24.1MP'),(5,5,'2600mAh'),(5,6,'8GB'),"
	      		+ "(6,2,'Android Wear'),(6,3,'512MB'),(6,4,'TI OMAP 3'),(6,5,'320mAh'),(6,6,'4GB'),"
	      		+ "(7,2,'Android Wear'),(7,3,'512MB'),(7,4,'Intel Atom'),(7,5,'360mAh'),(7,6,'4GB'),"
	      		+ "(8,1,'1.3MP'),(8,2,'Windows 8'),(8,3,'4GB'),(8,4,'Intel i3'),(8,5,'5200mAh'),(8,6,'256GB'),"
	      		+ "(9,1,'2MP'),(9,2,'Windows 10'),(9,3,'8GB'),(9,4,'Intel i7'),(9,5,'7500mAh'),(9,6,'512GB SSD'),"
	      		+ "(10,1,'1.3MP'),(10,2,'Windows 8'),(10,3,'4GB'),(10,4,'Intel i5'),(10,5,'4500mAh'),(10,6,'256GB');";
	      
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "123");
	         stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	         c.setAutoCommit(false);
	         
	         stmt.addBatch(drop_ord_details);
	         stmt.addBatch(drop_emp);
	         stmt.addBatch(drop_spec_value);
	         stmt.addBatch(drop_spec_type);
	         stmt.addBatch(drop_ord);
	         stmt.addBatch(drop_pro);
	         stmt.addBatch(drop_cat);
	         stmt.addBatch(drop_cus);
	         stmt.executeBatch();
	         System.out.println("============DROPPED TABLES=============");
	         stmt.addBatch(drop_type_desig);
	         stmt.addBatch(drop_type_cat);
	         stmt.addBatch(drop_type_status);
	         stmt.executeBatch();
	         System.out.println("============DROPPED ENUMS=============");
	         stmt.addBatch(designation_type_enum);
	         stmt.addBatch(status_type_enum);
	         stmt.addBatch(category_type_enum);
	         stmt.addBatch(create_cus);
	         stmt.addBatch(create_emp);
	         stmt.addBatch(create_cat);
	         stmt.addBatch(create_pro);
	         stmt.addBatch(create_ord);
	         stmt.addBatch(create_ord_details);
	         stmt.addBatch(create_spec_types);
	         stmt.addBatch(create_spec_values);
	         stmt.executeBatch();
	         System.out.println("============CREATED TABLES=============");
	         stmt.addBatch(insert_cus_1);
	         stmt.addBatch(insert_cus_2);
	         stmt.addBatch(insert_cus_3);
	         stmt.addBatch(insert_cus_4);
	         stmt.addBatch(insert_cus_5);
	         stmt.addBatch(insert_cus_6);
	         stmt.addBatch(insert_cus_7);
	         stmt.addBatch(insert_cus_8);
	         stmt.addBatch(insert_cus_9);
	         stmt.addBatch(insert_cus_10);
	         stmt.executeBatch();
	         System.out.println("==============CUSTOMERS inserted===============");
	         stmt.addBatch(insert_emp_a);
	         stmt.addBatch(insert_emp_b);
	         stmt.addBatch(insert_emp_c);
	         stmt.addBatch(insert_emp_d);
	         stmt.addBatch(insert_emp_e);
	         stmt.addBatch(insert_emp_f);
	         stmt.addBatch(insert_emp_g);
	         stmt.addBatch(insert_emp_h);
	         stmt.addBatch(insert_emp_i);
	         stmt.addBatch(insert_emp_j);
	         stmt.addBatch(insert_emp_k);
	         stmt.executeBatch();
	         System.out.println("==============EMPLOYEES inserted===============");
	         stmt.addBatch(insert_cat_1);
	         stmt.addBatch(insert_cat_2);
	         stmt.addBatch(insert_cat_3);
	         stmt.addBatch(insert_cat_4);
	         stmt.executeBatch();
	         System.out.println("==============CATEGORY inserted===============");
	         stmt.addBatch(insert_pro_1);
	         stmt.addBatch(insert_pro_2);
	         stmt.addBatch(insert_pro_3);
	         stmt.addBatch(insert_pro_4);
	         stmt.addBatch(insert_pro_5);
	         stmt.addBatch(insert_pro_6);
	         stmt.addBatch(insert_pro_7);
	         stmt.addBatch(insert_pro_8);
	         stmt.addBatch(insert_pro_9);
	         stmt.addBatch(insert_pro_10);
	         stmt.executeBatch();
	         System.out.println("==============PRODUCTS inserted===============");
	         stmt.addBatch(insert_ord_1);
	         stmt.addBatch(insert_ord_2);
	         stmt.addBatch(insert_ord_3);
	         stmt.addBatch(insert_ord_4);
	         stmt.addBatch(insert_ord_5);
	         stmt.addBatch(insert_ord_6);
	         stmt.addBatch(insert_ord_7);
	         stmt.addBatch(insert_ord_8);
	         stmt.addBatch(insert_ord_9);
	         stmt.addBatch(insert_ord_10);
	         stmt.executeBatch();
	         System.out.println("==============ORDERS inserted===============");
	         stmt.addBatch(insert_ord_det_1);
	         stmt.addBatch(insert_ord_det_2);
	         stmt.addBatch(insert_ord_det_3);
	         stmt.addBatch(insert_ord_det_4);
	         stmt.addBatch(insert_ord_det_5);
	         stmt.addBatch(insert_ord_det_6);
	         stmt.addBatch(insert_ord_det_7);
	         stmt.addBatch(insert_ord_det_8);
	         stmt.addBatch(insert_ord_det_9);
	         stmt.addBatch(insert_ord_det_10);
	         stmt.addBatch(insert_ord_det_11);
	         stmt.addBatch(insert_ord_det_12);
	         stmt.addBatch(insert_ord_det_13);
	         stmt.addBatch(insert_ord_det_14);
	         stmt.addBatch(insert_ord_det_15);
	         stmt.addBatch(insert_ord_det_16);
	         stmt.addBatch(insert_ord_det_17);
	         stmt.addBatch(insert_ord_det_18);
	         stmt.addBatch(insert_ord_det_19);
	         stmt.addBatch(insert_ord_det_20);
	         stmt.executeBatch();
	         System.out.println("==============ORDER_DETAILS inserted===============");
	         stmt.addBatch(insert_spec_type_1);
	         stmt.addBatch(insert_spec_type_2);
	         stmt.addBatch(insert_spec_type_3);
	         stmt.addBatch(insert_spec_type_4);
	         stmt.addBatch(insert_spec_type_5);
	         stmt.addBatch(insert_spec_type_6);
	         stmt.executeBatch();
	         System.out.println("==============SPEC_TYPES inserted===============");
	         stmt.addBatch(insert_spec_value);
	         stmt.executeBatch();
	         System.out.println("==============SPEC_VALUES inserted===============");

	         stmt.executeBatch();
	         c.commit();
	         stmt.close();
	         c.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Operations successful");
	   }
}
