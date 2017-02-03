<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="script/login.js"></script>
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
	if(user_in != null){
	response.sendRedirect("myaccount.jsp");
	}
%>
<div class="container">
	<div class="register-div pull-left">
		<h2>NEW CUSTOMERS</h2>
		<button class="btn btn-primary new-user-btn">Create new user</button>
		<br>
		<div class=" form-group">
		<form class="register-form" method="post" action="Register" style="display:<% 
		String newUser = (String) session.getAttribute("newUser");
		if (newUser == null){ %> 
		none;" >
		<%}
		else{%> init;"><%}%>
			<div class=" form-group">
			<label for="newusername">Username: </label>
			<input type="text" placeholder="Username" class="form-control" id="newusername" name="new-username" required>
			</div> 
			<%
				if (newUser != null && newUser.equals("false")) {
			%>
			<p class="alert text-warning">Username is taken, please enter a new one.</p>
			<%
				session.setAttribute("newUser", null);
				}
			%> 
			<div class=" from-group">
			<label for="newpassword">Password: </label>
			<input type="password" placeholder="Password" class="form-control" id="newpassword" name="new-password" required><br>
			</div>
			<div class=" from-group">
			<input type="submit" class="btn btn-primary"value="Register" id="register-btn">
			</div>
		</form>
		
		<%
			if(newUser != null && newUser.equals("true")){
		%>
			<p class="success">Account created! Please log in.</p>
		<%
				session.setAttribute("newUser", null);
			}
		%>
		</div>
	</div>
	
	
	<div class="login-div pull-right">
		<h2>REGISTERED CUSTOMERS</h2>
		<div class=" form-group">
		<form class="login-form" method="post" action="Login">
		<div class="from-group">
			<label for="username">Username:</label>
			<input type="text" placeholder="Username" class="form-control" id="username" name="username" required>
		</div>
		<div class="from-group">
		<label for="password">Password:</label>
		<input type="password" placeholder="Password" class="form-control" id="password" name="password" required>
		</div>
			<%
				String invalidUser = (String) session.getAttribute("invalidUser");
				if (invalidUser != null) {
			%>
			<p class="alert text-warning">Invalid username or password, please enter again!</p>
			<%
				session.setAttribute("invalidUser", null);
				}
			%>
		<div class="from-group">
			<input type="submit" class="btn btn-primary" value="Login"> 
		</div>
		</form>
		</div>
	</div>
</div>
</body>
</html>