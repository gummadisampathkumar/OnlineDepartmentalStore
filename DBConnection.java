package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
	
	
	private static Connection con;
	
	private DBConnection(){
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Online_Department_Store","root","root");
			System.out.println("Connection established");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getConnection() {
		if(con == null) {
			DBConnection connection = new DBConnection();
			return con;
		}
		else {
			return con;
		}
		
	}
	public static void main(String[] args) {
		getConnection();
	}
}
