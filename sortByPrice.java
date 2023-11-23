package com.training.java.DAO;

import java.util.Comparator;

import com.training.java.pojo.Product;

public class sortByPrice implements Comparator<Product>{

	@Override
	public int compare(Product o1, Product o2) {
		// TODO Auto-generated method stub
		return Double.compare(o1.getSellingPrice(), o2.getSellingPrice());
	}

}
