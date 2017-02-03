package com.cart.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.cart.dao.UserDAO;
import com.cart.dao.WishlistDAO;
import com.cart.model.Wishlist;

/**
 * Servlet implementation class SaveWishlist
 */
@WebServlet("/CreateWishlist")
public class CreateWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public CreateWishlist() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user_in = (String) session.getAttribute("user");
		System.out.println(user_in+" CreateWishlist");
		if(user_in == null){
			response.sendRedirect("login.jsp");
		}else{
			WishlistDAO wishlistDAO = new WishlistDAO();
			Wishlist wishlist = new Wishlist();
			UserDAO userDAO = new UserDAO();
			int uid = userDAO.getUserId(user_in);
			String name = request.getParameter("new-wishlist-name");
			if(name == null) name="new list";
			System.out.println(name+" CreateWishlist");
			String description = request.getParameter("new-wishlist-desc");
			System.out.println(description+" CreateWishlist");
//			if(description == null) description="";
			wishlist.setName(name);
			wishlist.setDescription(description);
			wishlistDAO.createWishlist(uid, wishlist);
			response.sendRedirect("wishlist.jsp");
		}
	}

}
