package com.group2.bean;

public class OrderDetails extends CartItem {

	private int order_id;
	private String start_date;
	private String end_date;
	private String status;
	private String order_date;
	private int cus_id;
	private String cus_fname;
	private String cus_lname;
	
	public String getCus_lname() {
		return cus_lname;
	}
	public void setCus_lname(String cus_lname) {
		this.cus_lname = cus_lname;
	}
	public int getCus_id() {
		return cus_id;
	}
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	public String getCus_fname() {
		return cus_fname;
	}
	public void setCus_fname(String cus_fname) {
		this.cus_fname = cus_fname;
	}
	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
