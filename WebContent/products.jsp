<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%!
	Connection con;

	public void jspInit(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/shoppingcart","root","password123");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
%>
</head>
<body>
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
<h2>PRODUCTS</h2>
		
<div class="container form-group">
	<form method="post" action="AddtoCart">
	<table class="table table-striped table-hover table-condensed">
	<tr>
		<th>PRODUCT</th>
		<th>PRICE</th>
	</tr>
<%
	try{
		ResultSet rs = con.createStatement().executeQuery("select * from product");
		while (rs.next()) {

%>
	<tr id="<%=rs.getInt(1)%>" >
		<td class="product-name"><input type="checkbox" name="product" value="<%=rs.getInt(1) %>"> <%=rs.getString(2) %></td>
		<td class="product-price" name="price"><%=rs.getDouble(3) %></td>
	</tr>		
<%		}

	}catch(Exception e){
		e.printStackTrace();
	}
%>
</table>
<input type="submit" name="submit" class="btn btn-primary" value="Add to Cart">
</form>
</div>
</div>
</body>
</html>