<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="AccountController?action=change" method="post">
					<p class="error">
					<%
						String message = (String) request.getAttribute("message");
						
						if (message == null) {
							message = "";	
						}
						out.println(message);		
					%>
					</p>
					<h1>Change Password</h1>
					<label for="oldPassword">Old Password</label>
					<input type="password" name="oldPassword">
					<label for="password">New Password</label>
					<input type="password" name="password">
					<label for="password1">Confirm Password</label>
					<input type="password" name="password1">
					<input type="submit" value="Change">
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />		