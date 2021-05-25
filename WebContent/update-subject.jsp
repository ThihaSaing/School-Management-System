<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ClassroomControlController?action=updateSubject" method="POST">
					<h1>Update Subject</h1>
					<input type="hidden" name="subjectID" value="${subjectData.subjectid}">
					<label>Subject name:</label>
					<input type="text" name="subjectName" value="${subjectData.subjectname}" />
					<input type="submit" value="Save" class="save" />
					<a href="ClassroomControlController?action=loadSubject">Back to List</a>
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />


	
		
	