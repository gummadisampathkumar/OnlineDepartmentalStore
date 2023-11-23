package com.training.exceptions;

public class ProductIDException extends Exception{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Product ID not found. Enter valid Product ID";
	}
	
	
}
