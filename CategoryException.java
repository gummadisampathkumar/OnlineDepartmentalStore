package com.training.exceptions;

public class CategoryException extends Exception{
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Category not found. Enter valid Category";
	}
}
