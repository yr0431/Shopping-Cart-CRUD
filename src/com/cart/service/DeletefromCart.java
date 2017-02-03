package com.cart.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DeletefromCart
 */
@WebServlet("/DeletefromCart")
public class DeletefromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletefromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HashMap<Integer, Integer> cartlist = (HashMap<Integer, Integer>) session.getAttribute("cartlist");
		String id = request.getParameter("id");
		cartlist.remove(Integer.parseInt(id));
		session.setAttribute("cartlist", cartlist);
		response.sendRedirect("cart.jsp");
	}

}
