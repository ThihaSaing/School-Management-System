<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ClassroomControlController?action=deleteSubject" method="POST">
					<h1>Delete Subject</h1>
					<input type="hidden" name="subjectID" value="${subjectData.subjectid}">
					<p><strong>Subject name: </strong> ${subjectData.subjectname}
					<input type="submit" value="Delete" class="delete" />
					<a href="ClassroomControlController?action=loadSubject">Back to List</a>
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />
	
	