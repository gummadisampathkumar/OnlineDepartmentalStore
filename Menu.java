package com.training.java.Client;

import java.sql.SQLException;
import java.util.Scanner;

import com.training.exceptions.CategoryException;
import com.training.exceptions.LoginException;
import com.training.exceptions.ProductIDException;
import com.training.exceptions.ProductNameException;
import com.training.java.Service.AdminService;
import com.training.login.AdminLogin;
import com.training.login.NewUser;

public class Menu {
	private Scanner sc;

	
	private AdminLogin login;
	private NewUser newUser;
	
	public  Menu() throws SQLException {
		sc = new Scanner(System.in);
		newUser = new NewUser();
		
		login = new AdminLogin();
	}
	
	public void menu() throws SQLException, ProductIDException, CategoryException, ProductNameException, LoginException {
		String ch = "y";
		
		
		while(ch.equals("y")) {
			System.out.println("Enter your choice");
			System.out.println("1. Existing user/admin login");
			System.out.println("2. New User Registration");
			System.out.println("3. Exit");
//			System.out.println("4. Exit");
			int choice = sc.nextInt();
			
			switch(choice) {
			
			case 1 :
				login.Login();
				break;
			case 2:
				newUser.newUserRegistration();
				break;
			case 3:
				System.exit(0);
			
			}
			System.out.println("Do you want to continue login page(y/n)");
			ch = sc.next();
		}
	}
	
}
