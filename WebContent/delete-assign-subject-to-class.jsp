<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ClassroomControlController?action=deleteAssignSubjectToClass" method="POST">
					<h1>Delete Assign Subject to Class</h1>
					<input type="hidden" name="id" value="${subjectClassDataList.id}">
					<p><strong>Class name:</strong> ${subjectClassDataList.classname} </p>
					<p><strong>Subject name:</strong> ${subjectClassDataList.subjectname} </p>
					<input type="submit" value="Delete" class="save" />
					<a href="ClassroomControlController?action=loadAssignSubjectToClass">Back to List</a>
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />

	
	
