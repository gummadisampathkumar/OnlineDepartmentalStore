package com.training.java.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.database.DBConnection;
import com.training.exceptions.CategoryException;
import com.training.java.pojo.Product;
import com.training.java.pojo.PurchasePojo;
import com.training.java.pojo.User;

public class CustomerDAO {
	private Connection con;
	private PreparedStatement stat;
	private List<Product> productList;
	private List<PurchasePojo> purchaseList;
	Savepoint  sp;
	private Scanner sc;
	private double totalbill =0;
	
	public CustomerDAO() throws SQLException {
		con = DBConnection.getConnection();
		productList = new ArrayList<Product>();
		purchaseList = new ArrayList<PurchasePojo>();
	
		sc = new Scanner(System.in);
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
	
	public void sortByPrice() {
		Collections.sort(productList, new sortByPrice());
		for (Product p : productList) {
			System.out.println("Product ID is "+p.getProductId());
			System.out.println("Product name is "+p.getProductName());
			System.out.println("Product price is "+p.getSellingPrice());
			System.out.println();
		}
	}
	
	
	public void searchByCategory(String category) throws CategoryException {
		boolean b = false;
		
		for (Product p : productList) {
			if(p.getCategory().equals(category)) {
				System.out.println("Product ID is "+p.getProductId());
				System.out.println("Product name is "+p.getProductName());
				System.out.println("Product price is "+p.getSellingPrice());
				b = true;
			}
			
			
		}
		if(!b) {
			System.out.println("Please enter valid category");
		}
	}
	
	public void buyProduct() throws SQLException {
		con.setAutoCommit(false);
			String ch = "y";
			while(ch.equals("y")) {
				System.out.println("Enter your choice");
				System.out.println("1. Purchase by productID");
				System.out.println("2. Purchase by product name");
				int choice = sc.nextInt();
				
				switch(choice) {
					
				case 1:
					   System.out.println("Enter the Product Id ");
		               int id=sc.nextInt();
		               stat=con.prepareStatement("Select * from Product where productID=?");
		               stat.setInt(1, id);
		               ResultSet res=stat.executeQuery();
		               if(res.next()) {
		            	   PurchasePojo pojo = new PurchasePojo();
		            	   pojo.setProductId(res.getInt(1));
		            	   pojo.setProductName(res.getString(2));
		            	   pojo.setPrice(res.getDouble(3));
		            	   pojo.setCategory(res.getString(5));
		            	   purchaseList.add(pojo);
		               }else {
		            	   System.out.println("Enter valid Product ID");
		            	   break;
		               }
		              sp = con.setSavepoint();
		               stat=con.prepareStatement("update Product set quantity_available = quantity_available -1 where productID= ?");
		               stat.setInt(1, id);
		               int r=stat.executeUpdate();
		               if(r > 0) {
		            	   System.out.println("Product added to cart successfully");
		               }
		               
		               break;
				case 2:
					System.out.println("Enter the Product Name ");
		            String name=sc.next();
		           
		            stat=con.prepareStatement("Select * from Product where product_name=?");
		            stat.setString(1, name);
		            ResultSet res1=stat.executeQuery();
		            if(res1.next()) {
		         	   PurchasePojo pojo = new PurchasePojo();
		         	   pojo.setProductId(res1.getInt(1));
		         	   pojo.setProductName(res1.getString(2));
		         	   pojo.setPrice(res1.getDouble(3));
		         	   pojo.setCategory(res1.getString(5));
		         	   purchaseList.add(pojo);
		            }
		            else {
		            	System.out.println("Enter valid product name");
		            }
		            sp=con.setSavepoint();
		            stat=con.prepareStatement("update Product set quantity_available = quantity_available -1 where product_name = ?");
		            stat.setString(1, name);
		            int re=stat.executeUpdate();
		            if(re > 0) {
		            	System.out.println("Product added to cart Successfully");
		            }
		            
		            break;
				}
				System.out.println("Do you want to continue buying more products(y/n)");
				ch = sc.next();
			
				
		}
		
		
		
	}
	
	public void printBill(List<User> userList) throws SQLException {
		
		for (User user : userList) {
			for(PurchasePojo bill: purchaseList)
	        {
//				System.out.println(bill.getPrice());
	            totalbill=totalbill+bill.getPrice();
	        }
	        int coin=0;
	        
	        if(!(user.getSuperCoins() <=0)) {
	        System.out.println("You have Super coins "+user.getSuperCoins());
	        System.out.println("how many Super coins do you want to readem");
	        coin=sc.nextInt();
	        }
	        System.out.println("Do you want to purchase (yes/no)");
	        String purchase = sc.next();
	        if(user.getSuperCoins()>=coin && purchase.equals("yes"))
	        {
		        stat=con.prepareStatement("update User set super_coins=? where username=?");
		        
		        stat.setInt(1, (int) (user.getSuperCoins()-coin));
		        stat.setString(2,user.getUsername() );
		        int r=stat.executeUpdate();
		        double redem=((coin/100)*5);
		        totalbill=totalbill-redem;
		        System.out.println("Product purchased successfully");
		        System.out.println("Your Bill is "+totalbill);
		        System.out.println("Thank You For Your Purchase Vist Again......");
		        con.commit();
		        purchaseList = null;
		        this.retreive();
	//	        con.close();
	        }
	        else
	        {
	        	purchaseList = null;
	            System.out.println("Enter the valid coins");
	            con.rollback(sp);
//	            con.close();
	        }
		}

        
	}
}
