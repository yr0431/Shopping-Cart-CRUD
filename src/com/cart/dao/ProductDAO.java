package com.cart.dao;

/**
 * @author Rui Yang
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cart.model.Product;

public class ProductDAO {
	Connection con;
	public ProductDAO() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/shoppingcart","root","password123");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public Product getProduct (int pid) {
		Product product = new Product();
		try{
			PreparedStatement ps=con.prepareStatement("select * from product where id= ?");
			ps.setInt(1, pid);
			ResultSet rs = ps.executeQuery();
			rs.next();
			product.setId(pid);
			product.setName(rs.getString(2));
			product.setPrice(rs.getDouble(3));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return product;
	}
}
