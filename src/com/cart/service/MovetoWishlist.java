package com.cart.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cart.dao.UserDAO;
import com.cart.dao.WishlistDAO;
import com.cart.model.User;
import com.cart.model.Wishlist;

/**
 * Servlet implementation class MovetoWishlist
 */
@WebServlet("/MovetoWishlist")
public class MovetoWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovetoWishlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    	HttpSession session = request.getSession();
//    	HashMap<Integer, Integer> cartlist = (HashMap<Integer, Integer>) session.getAttribute("cartlist");
//		String[] widarr = request.getParameterValues("wishlist");
//		String user_in = (String) session.getAttribute("user");
//		String pid = request.getParameter("pid");
//
//		WishlistDAO wishlistDAO = new WishlistDAO();
//		
//		if(user_in == null){
//			response.sendRedirect("login.jsp");
//		}else{
//			UserDAO user = new UserDAO();
//			int uid = user.getUserId(user_in);
//			boolean existWishlist = wishlistDAO.existWishlist(uid);
//				if(widarr != null){
//					for(String wid : widarr){
//						wishlistDAO.addtoWishlist(Integer.parseInt(pid), Integer.parseInt(wid));
//					}
//					cartlist.remove(pid);
//					session.setAttribute("cartlist", cartlist);
//					response.sendRedirect("wishlist.jsp");
//				}
//		}
//    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HashMap<Integer, Integer> cartlist = (HashMap<Integer, Integer>) session.getAttribute("cartlist");
		String user_in = (String) session.getAttribute("user");
		String pid = request.getParameter("pid");
		String[] widarr = request.getParameterValues("wishlist");
		WishlistDAO wishlistDAO = new WishlistDAO();
		
		if(user_in == null){
			response.sendRedirect("login.jsp");
		}else{
			UserDAO user = new UserDAO();
			int uid = user.getUserId(user_in);
			boolean existWishlist = wishlistDAO.existWishlist(uid);
			if(existWishlist){
				String pidString = (String) session.getAttribute("pid");
				if(widarr != null && pidString != null){
					for(String wid : widarr){
						int x = wishlistDAO.duplicateWishprod(Integer.parseInt(wid), Integer.parseInt(pidString));
						if(x==0){
							wishlistDAO.addtoWishlist( Integer.parseInt(wid), Integer.parseInt(pidString));
						}
					}
					
//					System.out.println(cartlist.get(Integer.parseInt(pid))+" --MovetoWishlist");
					System.out.println(cartlist.get(Integer.parseInt(pidString))+" --MovetoWishlist");
					cartlist.remove(Integer.parseInt(pidString));
					session.setAttribute("pid",null);
					session.setAttribute("cartlist", cartlist);
					response.sendRedirect("wishlist.jsp");
				}else if (widarr == null && pidString != null) {
					session.setAttribute("pid",null);
					response.sendRedirect("cart.jsp");
				}else if (pid != null){
					session.setAttribute("pid", pid);
					response.sendRedirect("wishlist.jsp?event=choose&uid="+uid+"&pid="+pid);
				}else{
					response.sendRedirect("cart.jsp");
				}
			}else{
				Wishlist wishlist = new Wishlist();
				String name="New Wishlist";
				String description="";
				wishlist.setName(name);
				wishlist.setDescription(description);
				wishlistDAO.createWishlist(uid, wishlist);

				List<Wishlist> arr=wishlistDAO.getWishlist(uid);
				int x = wishlistDAO.duplicateWishprod(arr.get(0).getId(), Integer.parseInt(pid));
				System.out.println(x+" --MovetoWishlist");
				if(x==0){
					wishlistDAO.addtoWishlist( arr.get(0).getId(), Integer.parseInt(pid));
					cartlist.remove(Integer.parseInt(pid));
					session.setAttribute("cartlist", cartlist);
				}

				response.sendRedirect("wishlist.jsp");
			}
		}
	}

}
