<%
	String account = (String) session.getAttribute("account");
	String role = (String) session.getAttribute("role");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<link rel="stylesheet" type="text/css" href="css/main.css">
		<link rel="stylesheet" type="text/css" href="css/form.css">
		<link rel="stylesheet" type="text/css" href="css/admin.css">
		<link rel="stylesheet" type="text/css" href="css/table.css">
		<link rel="stylesheet" type="text/css" href="css/profile.css">
		<link rel="stylesheet" type="text/css" href="css/news.css">
		<title>School Management System</title>
	</head>
	<body>
		<header>
			<img src="img/foobarLogo.jpg" alt="logo" class="logo" height="50">
			<div class="welcome-box">
				<% if (account != null) { %> 
				<p>
					Welcome, <strong><%= account %></strong>!<br>
					<a href="logout.jsp">Logout?</a>
				</p>
				<% } %>
			</div>
			<nav>
				<a href="Index">Home</a><!--   
			 --><a href="News">News</a><!--  
			 --><a href="policy.jsp">Policy</a><!-- 
			 --><a href="about.jsp">About</a><!-- 
			 	<% if(account == null) { %>
			 --><a href="login.jsp">Login</a>
			 	<% } else if (role.equals("admin")) { %>
			 --><a href="panel.jsp">Admin Panel</a>
			 	<% } else if (role.equals("student")) { %>
			 --><a href="StudentController">Profile</a><!-- 
			 --><a href="StudentController?action=classInfo">Class Info</a>
			 	<% } else if (role.equals("teacher")) { %>
			 --><a href="TeacherController">Profile</a><!-- 
			 --><a href="TeacherClassInfoController">Class Info</a>
			 	<% } %>
			</nav>
		</header>		