package com.cart.dao;

/**
 * @author Rui Yang
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cart.model.User;

public class UserDAO {
	Connection con;
	public UserDAO() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/shoppingcart","root","password123");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean isUser(User user) {
		boolean x = false;
		try{
			PreparedStatement ps = con.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()) x=true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int insertUser(User user) {
		int x =0;
		try{
			PreparedStatement ps = con.prepareStatement("select * from user where username=?");
			ps.setString(1, user.getUsername());
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){		
				ps = con.prepareStatement("insert into user (username, password) values(?,?)");
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				x = ps.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int getUserId(String username) {
		int id = 0;
		try{
			PreparedStatement ps = con.prepareStatement("select id from user where username=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			id = Integer.parseInt(rs.getString(1));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
