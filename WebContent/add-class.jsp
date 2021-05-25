<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ClassroomControlController?action=addClass" method="POST">
					<h1>Add Class</h1>
					<label for="className">Class name:</label>
					<input type="text" name="className" id="className" />
					<input type="submit" value="Add"/>
				</form>
			<% } else { %>
				<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	
	