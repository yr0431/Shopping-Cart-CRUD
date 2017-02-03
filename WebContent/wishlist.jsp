<%@page import="com.cart.dao.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.cart.dao.WishlistDAO" %>
<%@ page import="com.cart.model.Wishlist" %>
<%@ page import="com.cart.model.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="script/wishlist.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:useBean id="obj" class="com.cart.dao.WishlistDAO"></jsp:useBean>
<jsp:useBean id="productDAO" class="com.cart.dao.ProductDAO"></jsp:useBean>
<jsp:useBean id="userDAO" class="com.cart.dao.UserDAO"></jsp:useBean>
<header>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp">MyShoppingCart</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="index.jsp">Home</a></li>
      <li><a href="cart.jsp">Cart</a></li>
      <li><a href="wishlist.jsp">Wishlist</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
      <% String user_in = (String)session.getAttribute("user");
      	if(user_in != null){
      %>
      <li><a href="myaccount.jsp"><span class=""></span>Hi <%=user_in %>! </a></li>
      <li><a href="myaccount.jsp"><span class="glyphicon glyphicon-user"></span> My account</a></li>
      <li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
      <%
      	}else{
      %>
      <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      <%
      	}
      %>
    </ul>
  </div>
</nav>
</header>
<%
	if(user_in == null){
	response.sendRedirect("login.jsp");
	}
	
	String event = request.getParameter("event");
	int uid = userDAO.getUserId(user_in);
	String pid = request.getParameter("pid");
%>
<div class="container">
	<h2><%=user_in%>'s wishlist</h2>
	<div class="container">
		<button class="btn new-wishlist-btn btn-primary">Create a new wishlist</button>
		<div class="container from-group">
		<form class="create-form" method="post" action="CreateWishlist" style="display:none;">
		<div class="container from-group">
		<label for="name">Name:</label>
	  	<input type="text" class="form-control" id="name" name="new-wishlist-name">
		</div>
		<div class="container from-group">
		<label for="comment">Description:</label>
	  	<textarea class="form-control" rows="2" id="comment" name="new-wishlist-desc"></textarea>
	  	</div>
	  	<br>
	  	<div class="container from-group">
		<input type="submit" class="btn save-wishlist-btn btn-primary" value="Save">
		<input type="button" class="btn cancel-btn btn-default" value="Cancel">
		</div>
		</form>
	</div>
</div>


<% if(event!=null &&event.equals("choose")) {
%>
<div class="container">
 	<p> Please select the wishlist to save the product "<%=productDAO.getProduct(Integer.parseInt(pid)).getName()%>". </p> 
	<div class="form-group">
	<form class="choose=form" method="get" action="MovetoWishlist">
	<ul class="list-group">
	<%
		for(Wishlist wishlist : obj.getWishlist(uid)){
	%>
	<li class="list list-group-item"><input type="checkbox" name="wishlist" value="<%=wishlist.getId() %>"><%= wishlist.getName()%>: <%= wishlist.getDescription()%></li>
	<%
		}
	%>
	
	<div class="form-group">
	<input type="submit" class="btn btn-primary" value="Submit">
	<a href="cart.jsp">
	<input type="button" class="btn btn-default" value="Cancel">
	</a>
	</ul>
	</div>
	</form>
	</div>
</div>
<%
	}
%>
<div class="container">
<dl>
	<%
		for(Wishlist wishlist : obj.getWishlist(uid)){
			int wid = wishlist.getId();
	%>
		<dt id="wishlist<%=wid %>"><%= wishlist.getName()%> <span class="wishlist-discription"> <%= wishlist.getDescription()%></span></dt>
    	<%
    		for(Product product : obj.getProduct(wid)){
    	%>
    	<dd id="wishprod<%=product.getId() %>"><%= product.getName() %> <%= product.getPrice() %> <a href="MovetoCart?wid=<%=wid %>&pid=<%=product.getId() %>">Move to cart</a> <a href="DeletefromWishlist?wid=<%=wid %>&pid=<%=product.getId() %>">Delete</a></dd>
	<%
    		}
    %>
    	<a href="DeleteWishlist?wid=<%=wid %>">
    	<input type="button" class="delete-btn btn btn-default" value="Delete this wishlist">
    	</a>
    <%
		}
	%>
	
</dl>
</div>
</div>
</body>
</html>