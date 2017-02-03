package com.cart.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cart.dao.WishlistDAO;

/**
 * Servlet implementation class DeletefromWishlist
 */
@WebServlet("/DeletefromWishlist")
public class DeletefromWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletefromWishlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String wid = request.getParameter("wid");
		String pid = request.getParameter("pid");
		WishlistDAO wishlistDAO = new WishlistDAO();
		wishlistDAO.deletefromWishlist(Integer.parseInt(pid), Integer.parseInt(wid));
		response.sendRedirect("wishlist.jsp");
	}

}
