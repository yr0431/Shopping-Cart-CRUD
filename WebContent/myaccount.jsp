<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
<% 
	if(user_in == null){
	response.sendRedirect("login.jsp");
	}
%>
<div class="container">
	<h2>Welcome <%=user_in%> !</h2>
	<br>
	<div>
		<a href="cart.jsp"><button class="btn btn-primary">My Shopping Cart</button></a>
		<a href="wishlist.jsp?event=view"><button class="btn btn-primary">My Wish List</button></a>
	</div>
</div>
</body>
</html>