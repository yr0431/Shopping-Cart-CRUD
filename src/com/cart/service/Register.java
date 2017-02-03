package com.cart.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.dao.UserDAO;
import com.cart.model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		User user = new User();
		String uname = request.getParameter("new-username");
		String pword = request.getParameter("new-password");
		user.setUsername(uname);
		user.setPassword(pword);
		UserDAO userDAO = new UserDAO();
		int x=userDAO.insertUser(user);
		if(x==0){
			session.setAttribute("newUser", "false");
		}else{
		session.setAttribute("newUser", "true");
		}
		response.sendRedirect("login.jsp");
	}

}
