<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="AccountController?action=delete" method="post">
					<h1>Delete Account</h1>
					<input type="hidden" name="accountID" value="${account.id}">
					<input type="hidden" name="roleID" value="${account.roleid}">
					<input type="hidden" name="role" value="${account.role}">
					<p>Are you sure you want to delete this account?</p>
					<p><strong>AccountID: </strong>${account.id}</p>
					<p><strong>Name: </strong>${account.name}</p>
					<p><strong>Email: </strong>${account.email}</p>
					<p><strong>Role: </strong>${account.role}</p>
					<input type="submit" value="Delete">
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />		