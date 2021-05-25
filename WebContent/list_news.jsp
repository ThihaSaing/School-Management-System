<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<main>
			<% if (role.equals("admin")) { %>
			<h1 class="table">News List</h1>
			<c:if test="${fn:length(news) > 0}">
			<table>
				<tr>
					<th style="min-width: 120px;">Title</th>
					<th>Content</th>
					<th style="min-width: 120px;">Action</th>
				</tr>
				<c:forEach var="newsInfo" items="${news}">
					<c:url var="updateLink" value="NewsControllerServlet">
						<c:param name="action" value="loadUpdate" />
						<c:param name="newsID" value="${newsInfo.newsId}" />
					</c:url>
					<c:url var="deleteLink" value="NewsControllerServlet">
						<c:param name="action" value="loadDelete" />
						<c:param name="newsID" value="${newsInfo.newsId}" />
					</c:url>
					<tr>
						<td>${newsInfo.newsTitle}</td>
						<td>${newsInfo.newsContent}</td>
						<td>
							<a href="${updateLink}">Update</a> |
							<a href="${deleteLink}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			</c:if>
			<c:if test="${fn:length(news) <= 0}">
				<b>No data</b>
			</c:if>
			<a href="add_news.jsp" class="button">Add News</a>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	