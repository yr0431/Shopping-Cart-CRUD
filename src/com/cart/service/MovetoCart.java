package com.cart.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.dao.WishlistDAO;

/**
 * Servlet implementation class MovetoCart
 */
@WebServlet("/MovetoCart")
public class MovetoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovetoCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int wid = Integer.parseInt(request.getParameter("wid"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		WishlistDAO wishlistDAO = new WishlistDAO();
		wishlistDAO.deletefromWishlist(pid, wid);
		HttpSession session = request.getSession();
		HashMap<Integer, Integer> cartlist = (HashMap<Integer, Integer>) session.getAttribute("cartlist");
		if (cartlist == null) {
			cartlist = new HashMap<Integer, Integer>();
		}
		
		if (cartlist.containsKey(pid)){
			int count = cartlist.get(pid);
			count++;
			cartlist.put(pid, count);
		} else {
			cartlist.put(pid, 1);
		}
		session.setAttribute("cartlist", cartlist);
		response.sendRedirect("cart.jsp");
		
	}

}
