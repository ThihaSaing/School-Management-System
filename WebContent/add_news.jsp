<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<form action="NewsControllerServlet?action=add" method="post">
			<h1>Add News</h1>
				<label for="title">Title</label>
				<input type="text" name="title">
				<label for="content">Content</label>
				<textarea rows="5" name="content"></textarea>
				<input type="submit" value="Add">
				<a href="NewsControllerServlet">Back to news list</a>
			</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />