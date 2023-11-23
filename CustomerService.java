package com.training.java.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.database.DBConnection;
import com.training.exceptions.CategoryException;
import com.training.java.DAO.CustomerDAO;
import com.training.java.pojo.User;

public class CustomerService {
	private Scanner sc;
	private CustomerDAO customerDAO;
	private Connection con;
	private PreparedStatement stat;
	
	
	public CustomerService() throws SQLException {
		sc = new Scanner(System.in);
		con = DBConnection.getConnection();
		customerDAO = new CustomerDAO();
		
		
	}
	public void sortByPrice() {
		customerDAO.sortByPrice();
	}
	
	public void searchByCategory() throws SQLException, CategoryException {
		System.out.println("List of Categories");
		stat = con.prepareStatement("select distinct category from Product");
		ResultSet set = stat.executeQuery();
		while(set.next()) {
			System.out.println(set.getString(1));
		}
		System.out.println("Enter the category which u want to display prodducts");
		String category = sc.next();
		customerDAO.searchByCategory(category);
	}
	public void buyProduct(List<User> userList) throws SQLException {
		customerDAO.buyProduct();
		customerDAO.printBill(userList);
	}
}
