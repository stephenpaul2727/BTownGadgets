package com.group2.bean;

public class CartItem extends Product {

	private int selectedQuantity;
	private float totalUnitPrice;
	public int getSelectedQuantity() {
		return selectedQuantity;
	}
	public void setSelectedQuantity(int selectedQuantity) {
		this.selectedQuantity = selectedQuantity;
	}
	public float getTotalUnitPrice() {
		return totalUnitPrice;
	}
	public void setTotalUnitPrice(float totalUnitPrice) {
		this.totalUnitPrice = totalUnitPrice;
	}
	
}
