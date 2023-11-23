package com.training.java.pojo;

public class User {
	
	private String username;
	private String email;
	private String password;
	private static double superCoins;
	
	private User user;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getSuperCoins() {
		return superCoins;
	}

	public void setSuperCoins(double superCoins) {
		this.superCoins = superCoins;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
