package com.training.java.pojo;

public class Product extends Item{
	
	private int productId;
	private String productName;
	private double sellingPrice;
	private int quantityAvaliable;
	
	private Product product;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getQuantityAvaliable() {
		return quantityAvaliable;
	}

	public void setQuantityAvaliable(int quantityAvaliable) {
		this.quantityAvaliable = quantityAvaliable;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}
