<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.sql.*"%>
<%@ page import="com.cart.dao.ProductDAO" %>
<%@ page import="com.cart.model.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="script/cart.js"></script>
</head>
<body>

<jsp:useBean id="obj" class="com.cart.dao.ProductDAO"></jsp:useBean>

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

<div class="container">
	<h2>SHOPPING CART</h2>
	<div>
		<table class="cart-table table table-striped table-hover table-condensed">
			<tr class="head-table">
				<th>PRODUCT</th>
				<th>PRICE</th>
				<th>QTY</th>
				<th></th>
				<th></th>
			</tr>
			<%
			HashMap<Integer, Integer> cartlist = (HashMap<Integer, Integer>)session.getAttribute("cartlist");
			if(cartlist != null){
				for(int key : cartlist.keySet()){
					Product product = obj.getProduct(key);
			%>
			<tr id="<%=key%>" class="cart-row">
				<td class="product-col"><%= product.getName() %></td>
				<td class="price-col"><%= product.getPrice() %></td>
				<td class="qty-col"><input type="text" class="qty-input" value="<%= cartlist.get(key) %>"><button class="qty-update-btn btn btn-primary" style="display:none;">Update</button></td>
				<td class="wishlist-col"><a href="MovetoWishlist?pid=<%=key%>"><button class="wishlist-btn btn btn-default">Move to wishlist</button></a></td>
				<td class="del-col"><a href="DeletefromCart?id=<%=key%>"><img src="images/delete.png"></a></td>
			</tr>
			<%}
			}%>
		</table>
	</div>
</div>
</body>
</html>