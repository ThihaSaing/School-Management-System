<%
	String account = (String) session.getAttribute("account");
%>
<jsp:include page="include/header.jsp" />
		<main>
			<% if (account == null) {%>
			<form action="AccountController?action=login" method="post">
				<h1>Login</h1>
				<p class="error">
					<%
						String message = (String) request.getAttribute("message");
						
						if (message == null) {
							message = "";	
						}
						out.println(message);		
					%>
				</p>
				<label for="email">Email</label>
				<input type="email" name="email">
				<label for="password">Password</label>
				<input type="password" name="password">
				<input type="submit" value="Login">
			</form>
			<% } else {			
				out.println("You are already logged in!");
			} %>
		</main>
<jsp:include page="include/footer.jsp" />		