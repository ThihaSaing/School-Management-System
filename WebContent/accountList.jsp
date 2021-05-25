<jsp:include page="include/header.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<h1 class="table">Account List</h1>
				<c:if test="${fn:length(accountList) > 0}">
				<table>
					<tr>
						<th>AccountID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Role</th>
						<th>Action</th>
					</tr>
					<c:forEach var="account" items="${accountList}">
						<c:url var="deleteLink" value="AccountController">
								<c:param name="action" value="confirmDelete" />
								<c:param name="accountID" value="${account.id}" />
						</c:url>
						<tr>
							<td>${account.id}</td>
							<td>${account.name}</td>
							<td>${account.email}</td>
							<td>${account.role}</td>
							<td><a href="${deleteLink}">Delete</a></td>
						</tr>
					</c:forEach>
				</table>
				</c:if>
				<c:if test="${fn:length(accountList) <= 0}">
					<b>No data</b>
				</c:if>
				<a href="createAccount.jsp" class="button">New Account</a>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />		