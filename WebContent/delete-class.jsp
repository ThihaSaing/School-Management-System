<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ClassroomControlController?action=deleteClass" method="POST">
					<h1>Delete Class</h1>
					<input type="hidden" name="classID" value="${classData.classid}">
					<p><strong>Class name: </strong> ${classData.classname}
					<input type="submit" value="Delete" class="delete" />
					<a href="ClassroomControlController">Back to List</a>
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />