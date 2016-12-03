package com.group2.bean;

import java.util.ArrayList;
import java.util.List;

public class Cart {

	private int selectedQuantity;
	private float totalCartPrice;
	
	public List<CartItem> cartItems = new ArrayList<CartItem>();
	
	public int getItemCount() {
		return cartItems.size();
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}
	
	public int getSelectedQuantity() {
		return selectedQuantity;
	}
	public void setSelectedQuantity(int selectedQuantity) {
		this.selectedQuantity = selectedQuantity;
	}
	public float getTotalCartPrice() {
		return totalCartPrice;
	}
	public void setTotalCartPrice(float totalCartPrice) {
		this.totalCartPrice = totalCartPrice;
	}
	
	public void addToCart(CartItem cartItem) {
		boolean isExists = false;
		for(int i = 0;i<cartItems.size();i++) {
			
			if(cartItem.getModel_name().equals(cartItems.get(i).getModel_name())) {
				isExists = true;
				int finalCount = cartItems.get(i).getSelectedQuantity() + cartItem.getSelectedQuantity();
				float updatedTotalUnitPrice = finalCount*cartItem.getPrice();
				cartItems.get(i).setSelectedQuantity(finalCount);
				cartItems.get(i).setTotalUnitPrice(updatedTotalUnitPrice);
			}
		}
		if(!isExists) {
			cartItems.add(cartItem);	
		}
		calculateCartPrice();
	}
	
	public void updateCart(int itemIndex, int updatedQuantity) {
		float updatedTotalUnitPrice = updatedQuantity*cartItems.get(itemIndex-1).getPrice();
		cartItems.get(itemIndex-1).setSelectedQuantity(updatedQuantity);
		cartItems.get(itemIndex-1).setTotalUnitPrice(updatedTotalUnitPrice);
		calculateCartPrice();
	}
	
	public void deleteCart(int itemIndex) {
		cartItems.remove(itemIndex-1);
		calculateCartPrice();
	}
	
	public void calculateCartPrice() {
		float i = 0;
		for(CartItem c : cartItems) {
			i = i + c.getTotalUnitPrice();
		}
		setTotalCartPrice(i);
	}
	
}
