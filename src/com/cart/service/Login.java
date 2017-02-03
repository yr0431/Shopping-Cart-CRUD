package com.cart.service;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.dao.UserDAO;
import com.cart.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String user_in = (String) session.getAttribute("user");
		User user = new User();
		String uname = request.getParameter("username");
		String pword = request.getParameter("password");
		user.setUsername(uname);
		user.setPassword(pword);
		UserDAO userDAO = new UserDAO();
		boolean isValid = userDAO.isUser(user);
		if (isValid) {
			user_in = user.getUsername(); 
			session.setAttribute("user", user_in);
			response.sendRedirect("myaccount.jsp");
		}else{
			session.setAttribute("invalidUser", user.getUsername());
			response.sendRedirect("login.jsp");
		}
		
	}

}
