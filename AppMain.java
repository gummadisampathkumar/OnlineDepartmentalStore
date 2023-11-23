package com.training.java.Main;

import java.sql.SQLException;

import com.training.exceptions.CategoryException;
import com.training.exceptions.LoginException;
import com.training.exceptions.ProductIDException;
import com.training.exceptions.ProductNameException;
import com.training.java.Client.Menu;

public class AppMain extends Thread{
	public static void main(String[] args)  {
		Menu menu;
		
		
			try {
				menu = new Menu();

				menu.menu();
			} catch (SQLException | ProductIDException | CategoryException | ProductNameException | LoginException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		
		
	}
}
