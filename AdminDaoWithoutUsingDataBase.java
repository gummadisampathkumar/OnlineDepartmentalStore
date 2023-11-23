package com.training.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.database.DBConnection;
import com.training.exceptions.CategoryException;
import com.training.exceptions.ProductIDException;
import com.training.exceptions.ProductNameException;
import com.training.java.Client.AdminClient;
import com.training.java.pojo.Product;

public class AdminDaoWithoutUsingDataBase {

	private Connection con;
	private PreparedStatement stat;
	private Scanner sc;
	private List<Product> productList;
	
	public AdminDaoWithoutUsingDataBase() throws SQLException {
		sc = new Scanner(System.in);
		con = DBConnection.getConnection();
		productList = new ArrayList<Product>();
		this.retreive();
		
	}
	
	public void retreive() throws SQLException {
		stat = con.prepareStatement("select * from Product");
		ResultSet set = stat.executeQuery();
		while(set.next()) {
			Product p = new Product();
			p.setProductId(set.getInt(1));
			p.setProductName(set.getString(2));
			p.setSellingPrice(set.getDouble(3));
			p.setQuantityAvaliable(set.getInt(4));
			p.setCategory(set.getString(5));
			p.setBuyingPrice(set.getDouble(6));
			productList.add(p);
		}
		
	}
	public void listOfProducts() throws SQLException {
		
		
		for (Product p : productList) {
			System.out.println("product id is "+p.getProductId());
			System.out.println("product name is "+p.getProductName());
			System.out.println("selling price is "+p.getSellingPrice());
			System.out.println("quantity available is "+p.getQuantityAvaliable());
			System.out.println("Category available is "+p.getCategory());
			System.out.println();
		}
	}
	
	public void searchProductByProductID(int productid) throws ProductIDException {
		boolean b = false;
		for (Product p : productList) {
			if(p.getProductId() == productid) {
				System.out.println("product name is "+p.getProductName());
				System.out.println("product price is "+p.getSellingPrice());
				System.out.println("quantity available is "+p.getQuantityAvaliable());
				System.out.println("Category is "+p.getCategory());
				System.out.println();
				b = true;
			}
		}
		if(!b) {
			System.out.println("Please enter the valid productID");
		}
		
	}
	
	public void listOfProductsByCategory(String category) throws CategoryException {
		boolean b = false;
		for (Product p : productList) {
			if(p.getCategory().equals(category)) {
				System.out.println("product id is "+p.getProductId());
				System.out.println("product name is "+p.getProductName());
				System.out.println("product price is "+p.getSellingPrice());
				System.out.println("quantity available is "+p.getQuantityAvaliable());
				System.out.println();
				b = true;
			}
			
		}
		if(!b) {
			System.out.println("Please enter a valid category");
		}
	}
	
	public void searchProductByName(String name) throws ProductNameException {
		boolean b = false;
		
		for (Product p : productList) {
			if(p.getProductName().equals(name)) {
				System.out.println("product id is "+p.getProductId());
				System.out.println("product name is "+p.getProductName());
				System.out.println("product price is "+p.getSellingPrice());
				System.out.println("quantity available is "+p.getQuantityAvaliable());
				System.out.println();
				b = true;
			}
		}
		if(!b) {
			System.out.println("Please enter valid Product name");
		}
	}
	
	
	public void totalAmount() {
		double total = 0;
		for (Product p : productList) {
			
			total = total + (p.getQuantityAvaliable() * p.getBuyingPrice());
		}
		System.out.println("Total amount spend on buying products is "+total);
		
	}
	
	public void viewProfitByCategory(String category) throws CategoryException {
		double total = 0;
		boolean b=false;
		for (Product p : productList) {
			if(p.getCategory().equals(category)) {
				total = total + (p.getQuantityAvaliable() * p.getBuyingPrice());
				b = true;
			}
		}
		if(!b) {
			System.out.println("Please enter valid Category");
		}else {
		System.out.println("Total profit on "+category+" is "+total);
	
		}
	}
	public void insertProducts(Product p) throws SQLException {
		
		//for (Product p : productlist) {
			stat = con.prepareStatement("insert into Product(productID,product_name,quantity_available,category,buying_price,selling_price) values (?,?,?,?,?,?) ");
			stat.setInt(1, p.getProductId());
			stat.setString(2, p.getProductName());
//			stat.setDouble(3, p.getSellingPrice());
			stat.setInt(3, p.getQuantityAvaliable());
			stat.setString(4, p.getCategory());
			stat.setDouble(5, p.getBuyingPrice());
			stat.setDouble(6, ((p.getBuyingPrice()*0.15)+p.getBuyingPrice()));
			int update = stat.executeUpdate();
			if(update > 0) {
				System.out.println("Data Inserted");
			}
			this.retreive();
			
		//}
	}

	
	
	
	
}



class AdminDAO{
	
	private Connection con;
	private PreparedStatement stat;
	private List<Product> productList;
	private double totalAmount;
	private double profitAmount;

	
	public AdminDAO() {
		con = DBConnection.getConnection();
		productList = new ArrayList<Product>();
		
	}
	
	
	
	
	
	public void listOfProducts() throws SQLException{
		
		stat = con.prepareStatement("select * from Product");
		ResultSet res = stat.executeQuery();
		while(res.next()) {
			System.out.println("Product ID is "+res.getInt(1));
			System.out.println("Product Name is "+res.getString(2));
			System.out.println("Selling price is "+res.getDouble(3));
			System.out.println("Quantity available is "+res.getInt(4));
			System.out.println("Category is "+res.getString(5));
			System.out.println("Buying Price is "+res.getDouble(6));
			System.out.println();
			
		}
		
		
		
	}
	public void searchProductByProductID(int productid) throws SQLException {
		
		stat = con.prepareStatement("select * from Product where productID = ?");
		stat.setInt(1, productid);
		ResultSet res = stat.executeQuery();
		if(res.next()) {
			System.out.println("Product ID is "+res.getInt(1));
			System.out.println("Product Name is "+res.getString(2));
			System.out.println("Selling price is "+res.getDouble(3));
			System.out.println("Quantity available is "+res.getInt(4));
			System.out.println("Category is "+res.getString(5));
			System.out.println("Buying Price is "+res.getDouble(6));
			System.out.println();
		}
		else {
			System.out.println("Product id not found");
		}
		
	}
	public void listOfProductsByCategory(String category) throws SQLException {
		stat = con.prepareStatement("select * from Product where category = ?");
		stat.setString(1, category);
		ResultSet res = stat.executeQuery();
		while (res.next()) {
			System.out.println("Product ID is "+res.getInt(1));
			System.out.println("Product Name is "+res.getString(2));
			System.out.println("Selling price is "+res.getDouble(3));
			System.out.println("Quantity available is "+res.getInt(4));
			System.out.println("Buying Price is "+res.getDouble(6));
			System.out.println();
		}
	}
	public void insertProduct(Product p) throws SQLException {
		
		//for (Product p : productlist) {
			stat = con.prepareStatement("insert into Product(productID,product_name,quantity_available,category,buying_price,selling_price) values (?,?,?,?,?,?) ");
			stat.setInt(1, p.getProductId());
			stat.setString(2, p.getProductName());
//			stat.setDouble(3, p.getSellingPrice());
			stat.setInt(3, p.getQuantityAvaliable());
			stat.setString(4, p.getCategory());
			stat.setDouble(5, p.getBuyingPrice());
			stat.setDouble(6, ((p.getBuyingPrice()*0.15)+p.getBuyingPrice()));
			int update = stat.executeUpdate();
			if(update > 0) {
				System.out.println("Data Inserted");
			}
			
		//}
	}
	
	public void searchProductByName(String name) throws SQLException {
		stat = con.prepareStatement("select * from Product where product_name = ?");
		stat.setString(1, name);
		ResultSet res = stat.executeQuery();
		if(res.next()) {
			System.out.println("Product ID is "+res.getInt(1));
			System.out.println("Product Name is "+res.getString(2));
			System.out.println("Selling price is "+res.getDouble(3));
			System.out.println("Quantity available is "+res.getInt(4));
			System.out.println("Buying Price is "+res.getDouble(6));
		}
		
	}
	public void totalAmount() throws SQLException {
		stat = con.prepareStatement("select buying_price*quantity_available from Product");
		ResultSet set = stat.executeQuery();
		totalAmount = 0.0;
		
		while(set.next()) {
			totalAmount = totalAmount + set.getDouble(1);
		}
		System.out.println("Total amount spend on buying products is "+totalAmount);
	}
	public void profitAmount() throws SQLException {
		stat = con.prepareStatement("select selling_price*quantity_available from Product");
		ResultSet set = stat.executeQuery();
		double sellingAmount = 0.0;
		
		while(set.next()) {
			sellingAmount = sellingAmount + set.getDouble(1);
		}
		profitAmount = sellingAmount-totalAmount;
		System.out.println("profit amount is "+ profitAmount);
	}
	
	
	
}















