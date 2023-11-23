package com.training.java.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.training.exceptions.CategoryException;
import com.training.exceptions.ProductIDException;
import com.training.exceptions.ProductNameException;
import com.training.java.DAO.AdminDaoWithoutUsingDataBase;
import com.training.java.pojo.Product;

public class AdminService {
	private AdminDaoWithoutUsingDataBase admindao;
	private Scanner sc;
	private List<Product> productlist;
	
	public AdminService() throws SQLException {
		admindao = new AdminDaoWithoutUsingDataBase();
		sc = new Scanner(System.in);
		productlist = new ArrayList<Product>();
	}
	
	public void listOfProducts() throws SQLException {
		admindao.listOfProducts();
	}
	
	
	
	public void searchProductByProductID() throws SQLException, ProductIDException {
		System.out.println("Enter the product id which u want to get details");
		int productid = sc.nextInt();
		admindao.searchProductByProductID(productid);
		
	}
	
	
	
	public void listOfProductsByCategory() throws SQLException, CategoryException {
		System.out.println("Enter the category name which u want to get details");
		String category = sc.next();
		admindao.listOfProductsByCategory(category);
	}
	
	
	public void insertProduct() throws SQLException {
		System.out.println("Enter no of products u want to insert");
		int noofproducts = sc.nextInt();
		for(int i=0 ;i<noofproducts ;i++) {
			Product p = new Product();
			
			System.out.println("Enter the product id ");
			p.setProductId(sc.nextInt());
			System.out.println("Enter ther product name");
			p.setProductName(sc.next());
//			System.out.println("Enter the selling price ");
//			p.setSellingPrice(sc.nextDouble());
			System.out.println("Enter the quantity");
			p.setQuantityAvaliable(sc.nextInt());
			System.out.println("Enter the category");
			p.setCategory(sc.next());
			System.out.println("Enter buying price");
			p.setBuyingPrice(sc.nextDouble());
			
			admindao.insertProducts(p);
			
		}
		
		//admindao.insertProduct(productlist);
	}
	public void totalAmount() throws SQLException {
		admindao.totalAmount();
	}
	public void profitAmount() throws SQLException, CategoryException {
		System.out.println("Enter the category");
		String category = sc.next();
				
		admindao.viewProfitByCategory(category);
	}
	public void searchProductByName() throws SQLException, ProductNameException {
		System.out.println("Enter the product name");
		String name = sc.next();
		admindao.searchProductByName(name);
		
	}
}
