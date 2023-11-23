package com.training.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.database.DBConnection;
import com.training.exceptions.CategoryException;
import com.training.exceptions.LoginException;
import com.training.exceptions.ProductIDException;
import com.training.exceptions.ProductNameException;
import com.training.java.Client.AdminClient;
import com.training.java.Client.CustomerClient;
import com.training.java.pojo.User;

public class AdminLogin {
	private Connection con;
	private PreparedStatement stat;
	private Scanner sc;
	private AdminClient adminclient;
	private CustomerClient customerClient;
	private List<User> userList ;
	
	public AdminLogin() throws SQLException {
		con = DBConnection.getConnection();
		sc = new Scanner(System.in);
		adminclient = new AdminClient();
		customerClient = new CustomerClient();
		userList = new ArrayList<User>();
	}
	public void Login() throws SQLException,ProductIDException, CategoryException, ProductNameException, LoginException {
		System.out.println("Enter username");
		String username = sc.next();
		System.out.println("Enter password");
		String password = sc.next();
		stat = con.prepareStatement("select * from User where username = ? and password = ?");
		stat.setString(1, username);
		stat.setString(2, password);
		ResultSet set = stat.executeQuery();
		if(set.next()) {
			if(set.getString(5).equals("Admin")) {
				System.out.println("Admin login successfull");
				adminclient.adminMenu();
			}
			else if(set.getString(5).equals("customer")){
				System.out.println("Customer login successfull");
				User u = new User();
				u.setUsername(set.getString(1));
				u.setPassword(set.getString(2));
				u.setSuperCoins(set.getDouble(4));
				userList.add(u);
				customerClient.customerMenu(userList);
			}
		}
		else {
			throw new LoginException();
		}
	}
}
