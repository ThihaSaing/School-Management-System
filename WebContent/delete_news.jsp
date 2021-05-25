<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
			<form action="NewsControllerServlet?action=delete" method="post">
				<h1>Delete News</h1>
				<input type="hidden" name="newsID" value="${news.newsId}">
				<p>Are you sure you want to delete this book?</p>
				<p><strong>Title: </strong><span>${news.newsTitle}</span></p>
				<p><strong>Content: </strong><span>${news.newsContent}</span></p>
				<input type="submit" value="Delete">
				<a href="NewsControllerServlet">Back to News list</a>
			</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	