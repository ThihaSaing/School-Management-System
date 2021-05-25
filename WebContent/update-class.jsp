<!DOCTYPE html>
<html>
<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
		<main>
			<% if (role.equals("admin")) { %>
				<form action="ClassroomControlController?action=updateClass" method="POST">
					<h1>Update Class</H1>
						<input type="hidden" name="classID" value="${classData.classid}">
						<label>Class name:</label>
						<input type="text" name="className" value="${classData.classname}" />
						<input type="submit" value="Save" class="save" />
						<a href="ClassroomControlController">Back to List</a>
				</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	

	