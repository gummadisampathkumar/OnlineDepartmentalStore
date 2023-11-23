package com.training.java.Client;

import java.sql.SQLException;
import java.util.Scanner;

import com.training.exceptions.CategoryException;
import com.training.exceptions.ProductIDException;
import com.training.exceptions.ProductNameException;
import com.training.java.Service.AdminService;

public class AdminClient {
	private Scanner sc;
	private AdminService adminservice;
	
	public AdminClient() throws SQLException {
		sc = new Scanner(System.in);
		adminservice = new AdminService();
	}
	
	public void adminMenu() throws SQLException, ProductIDException, CategoryException, ProductNameException {
		
		String ch = "y";
		
		
		while(ch.equals("y")) {
			System.out.println("Enter your choice");
			System.out.println("1. List of products in store");
			System.out.println("2. search product by productId");
			System.out.println("3. List of products by category");
			System.out.println("4. search product by productName");
			System.out.println("5. check total amount spend on products");
			System.out.println("6. Dispaly the profit by Category");
			System.out.println("7. Insert Product");
			
			int choice = sc.nextInt();
			
			switch(choice) {
			
			case 1 :
				adminservice.listOfProducts();
				break;
			case 2:
				adminservice.searchProductByProductID();
				break;
			case 3:
				adminservice.listOfProductsByCategory();
				break;
			case 4:
				adminservice.searchProductByName();
				break;
			case 5:
				adminservice.totalAmount();
				break;
			case 6:
				adminservice.profitAmount();
				break;
			case 7:
				adminservice.insertProduct();
				break;
			}
			System.out.println("Do you want to continue admin page(y/n)");
			ch = sc.next();
		}
	}
	
}
