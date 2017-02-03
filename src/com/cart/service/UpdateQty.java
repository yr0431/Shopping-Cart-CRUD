package com.cart.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UpdateQty
 */
@WebServlet("/UpdateQty")
public class UpdateQty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQty() {
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
		String qty = request.getParameter("qty");
		if(qty.equals("0")){
			response.sendRedirect("DeletefromCart?id="+id);
		}else{
			cartlist.put(Integer.parseInt(id),Integer.parseInt(qty));
			session.setAttribute("cartlist", cartlist);
			response.sendRedirect("cart.jsp");
		}
	}

}
