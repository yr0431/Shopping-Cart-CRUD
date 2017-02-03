package com.cart.dao;

/**
 * @author Rui Yang
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cart.model.Product;
import com.cart.model.Wishlist;

public class WishlistDAO {
	Connection con;
	public WishlistDAO() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/shoppingcart","root","password123");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int duplicateWishprod(int wid, int pid) {
		int x=0;
		try{
			PreparedStatement ps = con.prepareStatement("select * from wishprod where wid=? and pid=?");
			ps.setInt(1, wid);
			ps.setInt(2, pid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				x++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int addtoWishlist(int wid, int pid){
		int x=0;
		System.out.println("in"+" --wishlistDAO");
		try{
			PreparedStatement ps = con.prepareStatement("insert into wishprod (wid, pid) values (?,?)");
			ps.setInt(1, wid);
			ps.setInt(2, pid);
			x= ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return x;
	}
	
	public int deleteWishlist(int wid) {
		int x=0;
		try{
			PreparedStatement ps = con.prepareStatement("delete from wishlist where id=?");
			ps.setInt(1, wid);
			x= ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int deletefromWishlist(int pid, int wid){
		int x=0;
		try{
			PreparedStatement ps = con.prepareStatement("delete from wishprod where wid=? and pid=?");
			ps.setInt(1, wid);
			ps.setInt(2, pid);
			x= ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return x;
	}
	
	public boolean existWishlist(int uid){
		boolean x = false;
		try{
			PreparedStatement ps = con.prepareStatement("select * from wishlist where uid = ?");
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				x=true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int createWishlist(int uid, Wishlist wishlist){
		int x=0;
		try{
			PreparedStatement ps = con.prepareStatement("insert into wishlist (name, description, uid) values (?,?,?)");
			ps.setString(1, wishlist.getName());
			ps.setString(2, wishlist.getDescription());
			ps.setInt(3, uid);
			x=ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return x;
	}
	
	public int editWishlist(int wid, Wishlist wishlist){
		int x=0;
		try{
			PreparedStatement ps = con.prepareStatement("update wishlist set name=?, description=? where id=?");
			ps.setString(1, wishlist.getName());
			ps.setString(2, wishlist.getDescription());
			ps.setInt(3, wid);
			x= ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return x;
	}
	
	public List<Wishlist> getWishlist(int uid){
		List<Wishlist> wishlist = new ArrayList<>();
		try{
			PreparedStatement ps = con.prepareStatement("select * from wishlist where uid = ?");
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Wishlist temp = new Wishlist();
				temp.setId(rs.getInt(1));
				temp.setName(rs.getString(2));
				temp.setDescription(rs.getString(3));
				wishlist.add(temp);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return wishlist;
	}
	
	public List<Product> getProduct(int wid){
		List<Product> product = new ArrayList<Product>();
		ProductDAO productDAO = new ProductDAO();
		try{
			PreparedStatement ps = con.prepareStatement("select * from wishprod where wid = ?");
			ps.setInt(1, wid);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Product temp = new Product();
				temp = productDAO.getProduct(rs.getInt(2));
				product.add(temp);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		return product;
	}
}
