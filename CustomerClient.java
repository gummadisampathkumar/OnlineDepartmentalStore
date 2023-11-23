package com.training.java.Client;

import java.sql.SQLException;
import java.util.*;

import com.training.exceptions.CategoryException;
import com.training.java.Service.AdminService;
import com.training.java.Service.CustomerService;
import com.training.java.pojo.User;



public class CustomerClient {
	private Scanner sc;
	private CustomerService customerService;
	private AdminService adminService;
	
	public CustomerClient() throws SQLException {
		sc = new Scanner(System.in);
		customerService = new CustomerService();
		adminService = new AdminService();
	}

	public void customerMenu(List<User> userList) throws SQLException, CategoryException {
		String ch = "y";
		
		
		while(ch.equals("y")) {
			System.out.println("Enter your choice");
			System.out.println("1. List of products in store");
			System.out.println("2. sort price low to high");
			System.out.println("3. search product by category");
			System.out.println("4. buy a product");
			
			int choice = sc.nextInt();
			
			switch(choice) {
			
			case 1 :
				adminService.listOfProducts();
				break;
			case 2:
				customerService.sortByPrice();
				break;
			case 3:
				customerService.searchByCategory();
				break;
			case 4:
				customerService.buyProduct(userList);
				break;
			
			
			}
			System.out.println("Do you want to continue user page(y/n)");
			ch = sc.next();
		}
	}
}
