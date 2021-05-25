<jsp:include page="include/header.jsp" />
<% String role = (String) session.getAttribute("role"); %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<main>
			<% if (role.equals("admin")) { %>
			<form action="TeacherControllerServlet?command=ADD" method="post">
			<h1>Add Teacher</h1>
			<label>Subject:</label>
			<select name="subject">
				<c:forEach var="subjects" items="${subjectList}">
					<option value = "${subjects.subjectid}">${subjects.subjectname}</option>
				</c:forEach>
			</select>
			<label>Name:</label>
			<input type="text" name="teacherName" />
			<label>Date of Birth(yyyy-mm-dd):</label>
			<input type="text" name="teacherDoB" />
			<label>Address:</label>
			<input type="text" name="teacherAddress" />
			<label>Phone No.:</label>
			<input type="text" name="teacherPh" />
			<label>NRIC:</label>
			<input type="text" name="teacherNRIC" />
			<input type="submit" value="Add"/>
		</form>
			<% } else { %>
			<p>You are not allowed to access this page!</p>
			<% } %>
		</main>
<jsp:include page="include/footer.jsp" />	