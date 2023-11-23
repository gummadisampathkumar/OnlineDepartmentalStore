package com.training.java.pojo;

public class Item{
	
	private String ItemName;
	private String category;
	private double buyingPrice;
	
	private Item item;

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
}
