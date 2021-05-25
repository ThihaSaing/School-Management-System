<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>				
				<form action="ClassroomControlController?action=addSubject" method="POST">
					<h1>Add Subject</h1>
					<label for = "subjectName">Subject name:</label>
					<input type="text" name="subjectName" id="subjectName"/>
					<input type="submit" value="Add" class="save" />
					<a href="ClassroomControlController?action=loadSubject">Back to List</a>
				</form>
					
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	