<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<form action="NewsControllerServlet?action=update" method="post">
				<h1>Update News</h1>
				<input type="hidden" name="newsID" value="${news.newsId}">
				<label for="title">Title</label>
				<input type="text" name="title" value="${news.newsTitle}">
				<label for="content">Content</label>
				<textarea rows="5" name="content">${news.newsContent}</textarea>
				<input type="submit" value="Update">
				<a href="NewsControllerServlet">Back to news list</a>
			</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	



