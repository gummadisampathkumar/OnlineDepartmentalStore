package com.training.exceptions;

public class LoginException extends Exception{

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Credentials.Please enter valid username and password";
	}
	
	
}
