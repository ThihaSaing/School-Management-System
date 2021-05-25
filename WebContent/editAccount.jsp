<jsp:include page="include/header.jsp" />
<% 
	String role = (String) session.getAttribute("role");
	String email = (String) session.getAttribute("email");
%>
		<main>
			<% if (role.equals("student")) { %>
				<form action="StudentController?action=edit" method="post">
					<p class="error">
					<%
						String message = (String) request.getAttribute("message");
						
						if (message == null) {
							message = "";	
						}
						out.println(message);		
					%>
					</p>
					<h1>Edit Account</h1>
					<label for="email">Email</label>
					<input type="email" name="email" value="${email}">
					<label for="oldPassword">Old Password</label>
					<input type="password" name="oldPassword">
					<label for="password">New Password</label>
					<input type="password" name="password">
					<label for="password1">Confirm Password</label>
					<input type="password" name="password1">
					<input type="submit" value="Edit">
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />		