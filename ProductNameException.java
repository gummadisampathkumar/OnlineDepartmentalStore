package com.training.exceptions;

public class ProductNameException extends Exception{
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Product Name not found. Enter valid Product Name";
	}
}
