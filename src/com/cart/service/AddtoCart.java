package com.cart.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class AddtoCart
 */
@WebServlet("/AddtoCart")
public class AddtoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddtoCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HashMap<Integer, Integer> cartlist = (HashMap<Integer, Integer>) session.getAttribute("cartlist");
		String[] arr = request.getParameterValues("product");
		if (cartlist == null) {
			cartlist = new HashMap<Integer, Integer>();
		}
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				if (cartlist.containsKey(Integer.parseInt(arr[i]))) {
					int count = cartlist.get(Integer.parseInt(arr[i]));
					count++;
					cartlist.put(Integer.parseInt(arr[i]), count);
				} else {
					cartlist.put(Integer.parseInt(arr[i]), 1);
				}
			}
		}			
		session.setAttribute("cartlist", cartlist);
		response.sendRedirect("cart.jsp");
	}

}
