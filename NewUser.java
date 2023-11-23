package com.training.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.database.DBConnection;

public class NewUser {
	
	private Connection con;
	private PreparedStatement stat;
	private Scanner sc;
	
	public NewUser() {
		sc = new Scanner(System.in);
		con = DBConnection.getConnection();
	}
	
	public void newUserRegistration() throws SQLException {
		
		stat = con.prepareStatement("insert into User values(?,?,?,?,?)");
		System.out.println("Enter the username");
		String username = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		System.out.println("Enter email id");
		String email = sc.next();
		
		stat.setString(1, username);
		stat.setString(2, password);
		stat.setString(3, email);
		stat.setDouble(4, 100);
		stat.setString(5, "customer");
		
		int executeUpdate = stat.executeUpdate();
		if(executeUpdate > 0) {
			System.out.println("New User Registration successfull");
			System.out.println("For new User Welcome bonus 100 suoercoins added to u r account");
		}
		
	}
}
