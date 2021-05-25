<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="AccountController?action=create" method="post">
					<h1>Add New Account</h1>
					<p class="error">
					<%
						String message = (String) request.getAttribute("message");
						
						if (message == null) {
							message = "";	
						}
						out.println(message);		
					%>
					</p>
					<label for="role">Role</label>
					<select name="role">
						<option value="student">Student</option>
						<option value="teacher">Teacher</option>
					</select>
					<label for="id">ID</label>
					<input type="text" name="id" required>
					<label for="email">Email</label>
					<input type="email" name="email" required>
					<label for="password">Password</label>
					<input type="password" name="password" required>
					<input type="submit" value="Add">
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />		